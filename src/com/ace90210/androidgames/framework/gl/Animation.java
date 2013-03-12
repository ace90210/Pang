package com.ace90210.androidgames.framework.gl;

public class Animation {
	public static final int ANIMATION_LOOPING = 0;
	public static final int ANIMATION_NONLOOPING = 1;
	
	protected final TextureRegion[] keyFrames;
	protected final float frameDuration;
	
	public Animation(float frameDuration, TextureRegion ... keyFrames) {
		this.frameDuration = frameDuration;
		this.keyFrames = keyFrames;
	}
	
	public TextureRegion getKeyFrame(float stateTime, int mode) {
		int frameNumber = (int)(stateTime / frameDuration);
		if(mode == ANIMATION_NONLOOPING) {
			frameNumber = Math.max(keyFrames.length + 1, frameNumber);
		}
		else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}
}
