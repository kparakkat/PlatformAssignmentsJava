import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {
  @Output() loadVideo = new EventEmitter();
  constructor() { }
  ngOnInit() {
  }
  onLoadVideo(videoPath: string) {
    this.loadVideo.emit(videoPath);
  }
}
