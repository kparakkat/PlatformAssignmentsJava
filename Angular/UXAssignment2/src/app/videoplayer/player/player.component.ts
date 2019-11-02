import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  @Input() playVideoPath: string;
  @Output() playVideo = new EventEmitter();
  @Output() pauseVideo = new EventEmitter();
  @Output() endedVideo = new EventEmitter();
  videoPlayer: HTMLVideoElement;
  constructor() { }
  ngOnInit() {
  }
  @ViewChild('videoPlayer')
  set mainVideoEl(el: ElementRef) {
    this.videoPlayer = el.nativeElement;
  }

  onPlayVideo() {
    this.playVideo.emit('clicked');
  }

  onPauseVideo() {
    this.pauseVideo.emit('clicked');
  }

  onEndedVideo() {
    this.endedVideo.emit('clicked');
  }
}
