package game.main.graphics;

import org.lwjgl.opengl.GL11;

public class RenderManager {

private long window;
	
	public RenderManager(long window) {
		this.window = window;
	}
	
	public void render() {
		GL11.glPushMatrix();
		
		// bind to the appropriate texture for this sprite
	    
		// translate to the right location and prepare to draw
		GL11.glTranslatef(0, 0, 0);		
	    GL11.glColor3f(1,1,1);
			
	    GL11.glBegin(GL11.GL_QUADS);
	    GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(-0.5f, -0.5f);
	    GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(0.5f, -0.5f);
	    GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(0.5f, 0.5f);
	    GL11.glTexCoord2f(0, 0);
	    GL11.glVertex2f(-0.5f, 0.5f);
	    GL11.glEnd();
	    
		
		
			
		// restore the model view matrix to prevent contamination
		GL11.glPopMatrix();
	}
}
