#author: Phu Ha Truong


PID=$1
video_id=$2

cd /home/tomcat/upload/
work_dir=`pwd`

#UPLOAD YOUTUBE

# if [[ $3 == "" ]]; then
# 	video_privacy="unlisted"
# fi
youtube-upload --title="$video_id" --privacy="public" --client-secrets=${work_dir}/resources/.my_client_secret.json --credentials-file=${work_dir}/resources/.youtube-upload-credentials.json ${work_dir}/${PID}/*${video_id}* > ${work_dir}/temporary/${PID}_log_upload_video_${video_id} 2>&1 &
wait

#UPDATE DATABASE

youtube_log=`wc -l ${work_dir}/temporary/${PID}_log_upload_video_${video_id} | cut -f1 -d' '`
if [ ${youtube_log} -eq 5 ]; then
	
	youtube_link=`tail -1 ${work_dir}/temporary/${PID}_log_upload_video_${video_id}`
	mysql -uAdmin -paBcD1234 -e "UPDATE musicplayerconnectordb.video SET LINK='${youtube_link}' WHERE VIDEO_ID='${video_id}';"
	mysql -uAdmin -paBcD1234 -e "INSERT INTO musicplayerconnectordb.project_detail SET VIDEO_ID='${video_id}', PROJECT_ID='${PID}', VIDEO_TYPE='1';"
fi

 



