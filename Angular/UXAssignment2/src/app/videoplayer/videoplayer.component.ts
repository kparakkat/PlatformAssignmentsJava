import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { PlayerComponent } from './player/player.component';

@Component({
  selector: 'app-videoplayer',
  templateUrl: './videoplayer.component.html',
  styleUrls: ['./videoplayer.component.css']
})
export class VideoplayerComponent implements OnInit {
  imagePlayPauseURL:string = "./assets/media/play.png"; 
  imageMuteUnMuteURL:string = "./assets/media/audio_mute.png"; 
  playVideoPath:string = "./assets/media/lasvegas.MOV";
  @ViewChild('childPlayer') player: PlayerComponent;
  constructor() { }
  ngOnInit() {
  }

  onToggleVideo(message: string) : void {
    if (!this.player.videoPlayer.paused)
    {
      this.player.videoPlayer.pause();
      this.imagePlayPauseURL = "./assets/media/play.png";
    }
    else
    {
      this.player.videoPlayer.play();
      this.imagePlayPauseURL = "./assets/media/pause.png";
    }
  }

  onPlayVideo(message: string) : void {
    this.imagePlayPauseURL = "./assets/media/pause.png";
  }

  onPauseVideo(message: string) : void {
    this.imagePlayPauseURL = "./assets/media/play.png";
  }

  onEndedVideo(message: string) : void {
    this.imagePlayPauseURL = "./assets/media/play.png";
  }

  onUpVolume(message: string) : void {
    volume: Number;
    let volume = this.player.videoPlayer.volume;
    if (volume <= 1)
    {
      volume = volume + 0.2;
      if (volume >= 1)
        volume = 1;
    }
    this.player.videoPlayer.volume = volume;
  }

  onDownVolume(message: string) : void {
    volume: Number;
    let volume = this.player.videoPlayer.volume;
    if (volume > 0)
    {
      volume = volume - 0.2;
      if (volume <= 0)
        volume = 0;
    }
    this.player.videoPlayer.volume = volume;
  }

  onMuteUnMuteAudio(message: string) : void {
    if (!this.player.videoPlayer.muted)
    {
      this.player.videoPlayer.muted = true;
      this.imageMuteUnMuteURL = "./assets/media/audio_up.png";
    }
    else
    {
      this.player.videoPlayer.muted = false;
      this.imageMuteUnMuteURL = "./assets/media/audio_mute.png";
    }
  }

  onLoadVideo(playVideoPath: string) : void {
    this.player.videoPlayer.src = playVideoPath;
    this.playVideoPath = playVideoPath;
    this.player.videoPlayer.play();
    this.imagePlayPauseURL = "./assets/media/pause.png";
  }
}
