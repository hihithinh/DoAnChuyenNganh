function geturl(){
	$('#studio').show();
	$('#view').hide();
	player.stopVideo();
	
	var vid1='{"url":"pUP3OIfI_bE","t":0}';
	var obj1=JSON.parse(vid1);
	var vid2='{"url":"P1ZqU48TJag","t":0}';
	var obj2=JSON.parse(vid2);
	var vid3='{"url":"TxLDUeHj_ZU","t":0}';
	var obj3=JSON.parse(vid3);
	var vid4='{"url":"l7wNZSXPsLY","t":0}';
	var obj4=JSON.parse(vid4);
	var url='https://viewsync.net/watch?v='+obj1.url+'&t='+obj1.t+'&v='+obj2.url+'&t='+obj2.t+'&v='+obj3.url+'&t='+obj3.t+'&v='+obj4.url+'&t='+obj4.t+'&mode=solo';

	document.getElementById('studio').src = url;
}

$( document ).ready(function() {
	$('#studio').hide();
}); 

//2. This code loads the IFrame Player API code asynchronously.
var tag = document.createElement('script');

tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
var player;
function onYouTubeIframeAPIReady() {
    player = new YT.Player('ytplayer', {
      height: '300',
      width: '560',
      videoId: 'M7lc1UVf-VE',
    });
}