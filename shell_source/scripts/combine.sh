#author: Phu Ha Truong


#input
#$1 is the project's ID / folder's name.
#$2 is the video's name on Youtube
#$3 is the boolean decice whether upload Youtube or not
#$4 is the video's privacy on Youtube

PID=$1


cd /home/tomcat/upload/
work_dir=`pwd`
list_file=`ls ${work_dir}/${PID}`

number_of_videos=`ls -1 ${work_dir}/${PID} | wc -l`
number_of_videos_less=`expr ${number_of_videos} - 1`

#determine to scale 2 or 3 videos per row base on the number of videos

if [ $number_of_videos_less -gt 4 ]; then
	col=3
elif [ $number_of_videos_less -gt 1 ] && [ $number_of_videos_less -lt 5 ]; then
	col=2
else 
	col=1
fi

if [ `expr $number_of_videos_less % $col` -eq 0 ]; then
	row=`expr $number_of_videos_less / $col`
else
 	row=`expr $number_of_videos_less / $col + 1`
fi

#scale of the BIG video 

scale_x=1024
scale_y=`expr 720 / $col \* $row`
#scale of each video

video_scale_x=`expr $scale_x / $col`
video_scale_y=`expr $scale_y / $row`

#RENDER THE SCRIPTS

printf "ffmpeg " > ${work_dir}/temporary/${PID}-video-combine.sh
printf "ffmpeg " > ${work_dir}/temporary/${PID}-audio-combine.sh


for fn in $list_file; do

	printf "%s %s " "-i" "$fn" >> ${work_dir}/temporary/${PID}-video-combine.sh
	printf "%s %s " "-i" "$fn" >> ${work_dir}/temporary/${PID}-audio-combine.sh
	

done	

printf "%s \"\n" "-filter_complex" >> ${work_dir}/temporary/${PID}-video-combine.sh
printf "%s amix=inputs=${number_of_videos_less}:duration=first:dropout_transition=3 temp_audio_combined.mp3" "-filter_complex" >> ${work_dir}/temporary/${PID}-audio-combine.sh

printf "nullsrc=size=%sx%s [base];\n" "$scale_x" "$scale_y" >> ${work_dir}/temporary/${PID}-video-combine.sh 
printf "[0:v] setpts=PTS-STARTPTS, scale=%sx%s [video_0];\n" "$scale_x" "$scale_y" >> ${work_dir}/temporary/${PID}-video-combine.sh

for i in `seq 1 $number_of_videos_less`; do

	printf "[%s:v] setpts=PTS-STARTPTS, scale=%sx%s [video_%s];\n" "$i" "$video_scale_x" "$video_scale_y" "$i" >> ${work_dir}/temporary/${PID}-video-combine.sh

done

for i in `seq 0 $number_of_videos_less`;do

	if [ $i -eq 0 ]; then 
		printf "[base][video_%s] overlay=shortest=1 [tmp%s];\n" "$i" "$i" >> ${work_dir}/temporary/${PID}-video-combine.sh
	else
		j=`expr $i - 1`
		k=`expr $j % $col`
		m=`expr $j / $col`
		if [ $i -eq $number_of_videos_less ]; then
			printf "[tmp%s][video_%s] overlay=shortest=1:x=%s:y=%s\n" "$j" "$i" "`expr $video_scale_x \* $k`" "`expr $video_scale_y \* $m`" >> ${work_dir}/temporary/${PID}-video-combine.sh
		else
			printf "[tmp%s][video_%s] overlay=shortest=1:x=%s:y=%s [tmp%s];\n" "$j" "$i" "`expr $video_scale_x \* $k`" "`expr $video_scale_y \* $m`" "$i">> ${work_dir}/temporary/${PID}-video-combine.sh
		fi
	fi		

done

printf "\"" >> ${work_dir}/temporary/${PID}-video-combine.sh
printf " -c:v libx264 ${work_dir}/${PID}/temp_video_combined.avi\n" >> ${work_dir}/temporary/${PID}-video-combine.sh

printf "ffmpeg -i temp_video_combined.avi -i temp_audio_combined.mp3 -c:v copy -c:a aac -strict experimental -map 0:v:0 -map 1:a:0 ${PID}.avi" >> ${work_dir}/temporary/${PID}-video-combine.sh

#EXECUTE THE SCRIPTS

chmod +x ${work_dir}/temporary/${PID}-video-combine.sh ${work_dir}/temporary/${PID}-audio-combine.sh

cp ${work_dir}/temporary/${PID}-video-combine.sh ${work_dir}/${PID}/video-combine.sh
cp ${work_dir}/temporary/${PID}-audio-combine.sh ${work_dir}/${PID}/audio-combine.sh

cd ${work_dir}/${PID}

./audio-combine.sh > temp_audiolog 2>&1 &
./video-combine.sh > temp_videolog 2>&1 &

wait

mv ${work_dir}/${PID}/${PID}.avi ${work_dir}/combined/${PID}.avi
rm -rf *.sh
rm -rf temp_*

# #UPLOAD YOUTUBE

# if [[ ${upload_youtube} == "youtube" ]]; then
# 	if [[ $4 == "" ]]; then
# 		video_privacy="unlisted"
# 	fi
# 	youtube-upload --title="$video_name" --privacy="$video_privacy" --client-secrets=../my_client_secret.json ${work_dir}/combined/${PID}.avi > log_update
# fi
