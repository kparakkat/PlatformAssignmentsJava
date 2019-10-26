// Variable
var video1 = document.getElementById('video1');

video1.onplay = function(){
	document.getElementById('play_pause').src = 'media/pause.png';
}

video1.onpause = function(){
	document.getElementById('play_pause').src = 'media/play.png';
}

video1.onended = function(){
	document.getElementById('play_pause').src = 'media/play.png';
}

// Toggle Video
function toggleVideo(){
	if (!video1.paused) {
		document.getElementById('play_pause').src = 'media/play.png';
		video1.pause();
	} else{
		document.getElementById('play_pause').src = 'media/pause.png';
		video1.play();
	}
}

// Mute Audio
function muteAudio(){
	if (!video1.muted) {
		video1.muted = true;
		document.getElementById('control_audio').src = 'media/audio_up.png';
	} else{
		video1.muted = false;
		document.getElementById('control_audio').src = 'media/audio_mute.png';
	}
}

// Up Volueme
function upVolueme()
{
	var volume = video1.volume;
	if (volume <= 1)
	{
		volume = volume + 0.2;
		if (volume >= 1)
		 	volume = 1;
	}
	video1.volume = volume;
}

// Down Volueme
function downVolueme()
{
	var volume = video1.volume;
	if (volume > 0)
	{
		volume = volume - 0.2;
		if (volume <= 0)
		 	volume = 0;
	}
	video1.volume = volume;
}

// Do Like
function doLike()
{
  var likeCount =  parseInt(document.getElementById('likeCount').textContent, 10);
  if (isNaN(likeCount))
     likeCount = 0;
  likeCount = likeCount + 1;
  document.getElementById("likeCount").textContent = likeCount;
}

// Do Unlike
function doUnLike()
{
  var unlikeCount =  parseInt(document.getElementById('unlikeCount').textContent, 10);
  if (isNaN(unlikeCount))
  	unlikeCount = 0;
   unlikeCount = unlikeCount + 1;
  document.getElementById("unlikeCount").textContent = unlikeCount;
}

// Load Video
function loadVideo(e){
	video1.src = e;
	document.getElementById('play_pause').src = 'media/pause.png';
	video1.play();
}
