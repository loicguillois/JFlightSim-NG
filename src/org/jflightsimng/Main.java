package org.jflightsimng;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.util.SkyFactory;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initDisplay();
        initAudio();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initAudio() {
        /* nature sound - keeps playing in a loop. */
        AudioNode audio_nature = new AudioNode(assetManager, "Sounds/Nature.ogg");
        audio_nature.setLooping(true);
        audio_nature.setPositional(true);
        audio_nature.setLocalTranslation(Vector3f.ZERO.clone());
        audio_nature.setVolume(2);
        audioRenderer.playSource(audio_nature);
    }

    private void initDisplay() {
        Spatial balloon = assetManager.loadModel("Models/Hot_Air_Balloon.j3o");

        // Fire
        ParticleEmitter fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material mat_red = new Material(assetManager, "MatDefs/Particle.j3md");
        mat_red.setTexture("m_Texture", assetManager.loadTexture("Textures/flame.png"));
        fire.setMaterial(mat_red);
        fire.setImagesX(2);
        fire.setImagesY(2); // 2x2 texture animation
        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(0f, 0.2f, 1f, 1f)); // yellow
        fire.setInitialVelocity(new Vector3f(0, 10, 0));
        fire.setStartSize(1f);
        fire.setEndSize(0.1f);
        fire.setGravity(0);
        fire.setLowLife(0.5f);
        fire.setHighLife(0.5f);
        fire.setVelocityVariation(0.1f);
        fire.move(13f, 7f, 5f);
        rootNode.attachChild(fire);

        // Light
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

        // Sky
        rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/BrightSky.dds", false));

        rootNode.attachChild(balloon);
    }
}
