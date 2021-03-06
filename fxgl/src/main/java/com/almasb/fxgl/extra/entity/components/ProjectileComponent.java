/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.extra.entity.components;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

/**
 * Generic projectile component.
 * Automatically rotates the entity based on velocity direction.
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class ProjectileComponent extends Component {

    private Point2D velocity;
    private double speed;

    public ProjectileComponent(Point2D direction, double speed) {
        this.velocity = direction.normalize().multiply(speed);
        this.speed = speed;
    }

    /**
     * @return velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * @return direction vector (normalized)
     */
    public Point2D getDirection() {
        return velocity.normalize();
    }

    /**
     * Set direction in which projectile is moving.
     *
     * @param direction the vector
     */
    public void setDirection(Point2D direction) {
        this.velocity = direction.normalize().multiply(speed);
        getEntity().rotateToVector(velocity);
    }

    /**
     * @return current speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed value
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        this.velocity = velocity.normalize().multiply(speed);
        getEntity().rotateToVector(velocity);
    }

    @Override
    public void onAdded() {
        entity.rotateToVector(velocity);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translate(velocity.multiply(tpf));
    }
}
