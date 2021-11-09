/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author asus
 */
public class SinglePerceptronHeart extends SinglePerceptron{
    public float decideOutput(float summation){
        final float THRESHOLD = 0;
        
        return summation < THRESHOLD ? 1f : 2f;
    };
}
