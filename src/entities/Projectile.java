package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import main.Game;
import org.json.JSONObject;
import utils.Constants;
import static utils.HelpMethods.CanMoveHere;
import utils.Utils;

/**
 * Hit enemies or player on contact
 * @author matti
 */
public class Projectile extends Entity{
    /** Speed, units per tick */
    private static final float SPEED = 3f;
    /** movement angle */
    private float angle;
    /** movement scomposed in orthogonal speed */
    private float xSpeed, ySpeed;
    /** current level tile data */
    private int[][] lvlData;
    /** grvity applied every update */
    private final float gravity = 0f;
    /** bounces before disappearing */
    private int bounces = 0;
    /** frames before disappearing */
    private int despawnCounter=100;

    /** Constructor, spawn projectile
     * @param x coordinate x
     * @param y coordinate y
     * @param angle movement angle
     */
    public Projectile(float x, float y, float angle) {
        super(x, y);
        this.angle = -angle;
        calcSpeeds();
        initHitbox(x, y, 4, 4);
    }
    
     public Projectile(Point.Float position, float angle) {
        super(position.x, position.y);
        this.angle = -angle;
        calcSpeeds();
        initHitbox(x, y, 4, 4);
    }
    
    /** calculate scomposed speeds from base speed and angle */ 
    private void calcSpeeds(){
        xSpeed = (float)Math.cos(this.angle)*SPEED;
        ySpeed = (float)Math.sin(this.angle)*SPEED;
    }
 
    /** render projectile in Graphics g
     * @param g 
     * @param offsetX 
     * @param offsetY */ 
    public void render(Graphics g, float offsetX, float offsetY){
        
        g.setColor(Color.black);
        g.fillRect((int)(hitbox.x*Game.SCALE + offsetX), (int)(hitbox.y*Game.SCALE + offsetY), (int)(hitbox.width*Game.SCALE), (int)(hitbox.height*Game.SCALE));
        if(Constants.DEBUG){
            drawHitbox(g, offsetX, offsetY);
        }
    }
 
    /**
     * update tick
    */
    @Override
    public void update() {
        super.update();
        updatePos();
        /*if(hitbox.intersects(p.hitbox)){
            System.out.println("DAMAGE");
            p.reset();
        }*/
        if(despawnCounter--==0){
            die();
        }
    }

    /** update position */
    private void updatePos() {
        if(CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.y += ySpeed;
            ySpeed += gravity;
            if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
                hitbox.x += xSpeed;
            }else {
                setAngle((float)(Math.PI/2+(Math.PI/2-getAngle())));
                bounces--;
            }
        }else{
            setAngle((float)-getAngle());
             bounces--;
            //initHitbox(0, 0, 5, 5);
            //;
        }
        if(bounces<0){
            Game.playing.flyingAmmos.removeProjectile(this);

        }
       
    }

    /** get movement angle
     * @return the movement angle */
    public float getAngle() {
        return angle;
    }
    
    /** set movement angle
     * @param angle the movement angle */
    public void setAngle(float angle) {
        this.angle = angle;
        calcSpeeds();
    }
    
    /** load level tiles data
     * @param data level tiles data*/
    public void loadLvlData(int[][] data) {
        this.lvlData = data;
    }
    /** disappear event */
    @Override
    public void die() {
        Game.playing.flyingAmmos.removeProjectile(this);
    }

    /** get remaning bounces before disappearing
     * @return remaning bounces number */
    public int getRemaningBounces() {
        return bounces;
    }

    
    /**
     * Get a JSON representation of the projectile
     * @return a JSON object representing this
     */
    public JSONObject toJSONObject(){
        return new JSONObject()
            .put("position", Utils.jsonMapper.pointToJSON(getPosition()))
            .put("angle", -getAngle())
            .put("bounces", getRemaningBounces())
            .put("despawnCounter", despawnCounter);
    }
    
    /**
     * Update the current projectile with the JSON data
     * @param obj the JSON object to be used to update status
     */
    public void updateWithJson(JSONObject obj){
        setPosition(Utils.jsonMapper.JSONTOPoint(obj.getJSONArray("position")));
        bounces = obj.getInt("bounces");
        despawnCounter = obj.getInt("despawnCounter");
    }
    
    /**
     * Create a projectile with a JSON representation
     * @param obj the JSON object used to create the enemy
     * @return the newly created projectile
     */
    public static Projectile fromJSON(JSONObject obj){
        Projectile created = new Projectile(Utils.jsonMapper.JSONTOPoint(obj.getJSONArray("position")), obj.getFloat("angle"));
        created.bounces = obj.getInt("bounces");
        created.despawnCounter = obj.getInt("despawnCounter");
        return created;
    }

}
