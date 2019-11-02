import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-controls',
  templateUrl: './controls.component.html',
  styleUrls: ['./controls.component.css']
})
export class ControlsComponent implements OnInit {
  @Input() imagePlayPauseURL:string;
  @Input() imageMuteUnMuteURL:string;
  @Output() toggleVideo = new EventEmitter();
  @Output() upVolume = new EventEmitter();
  @Output() downVolume = new EventEmitter();
  @Output() muteUnMuteAudio = new EventEmitter();
  likeCount: number = 0;
  unlikeCount: number = 0;
  
  constructor() { 
  }
  ngOnInit() {
  }

  onToggleVideo() {
    this.toggleVideo.emit('clicked');
  }

  onUpVolume() {
    this.upVolume.emit('clicked');
  }

  onDownVolume() {
    this.downVolume.emit('clicked');
  }

  onMuteUnMuteAudio() {
    this.muteUnMuteAudio.emit('clicked');
  }

  onDoLike() {
      ++this.likeCount;
  }

  onDoUnLike() {
    ++this.unlikeCount;
  }
}
