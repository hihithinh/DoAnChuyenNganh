#author: Phu Ha Truong


PID=$1
video_id=$2
video_name=$3

if [ ${video_name} == "" ]; then
	$video_name=$video_id
fi

cd /home/tomcat/upload/
work_dir=`pwd`

#UPLOAD YOUTUBE

# if [[ $3 == "" ]]; then
# 	video_privacy="unlisted"
# fi
youtube-upload --title="$video_name" --privacy="unlisted" --client-secrets=${work_dir}/resources/.my_client_secret.json --credentials-file=${work_dir}/resources/.youtube-upload-credentials.json ${work_dir}/combined/${PID}.avi > ${work_dir}/temporary/${PID}_log_upload 2>&1 &
wait

#XU LY MYSQL  
# youtube_log=`wc -l ${work_dir}/temporary/${PID}_log_upload | cut -f1 -d' '`
# if [ ${youtube_log} -eq 5 ]; then
	
# 	youtube_link=`tail -1 ${work_dir}/temporary/${PID}_log_upload`
# 	mysql -uAdmin -paBcD1234 -e "UPDATE `musicplayerconnectordb`.`video` SET `LINK`='${youtube_link}' WHERE `VIDEO_ID`='${video_id}';"
# 	mysql -uAdmin -paBcD1234 -e "INSERT INTO `musicplayerconnectordb`.`project_detail` SET `VIDEO_ID`='${video_id}', `PROJECT_ID`='${PID}', `VIDEO_TYPE`='1';"
# fi

 



