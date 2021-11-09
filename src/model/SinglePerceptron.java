package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public class SinglePerceptron {
    private final float BIAS = 1;
    private float learningRate;

    public SinglePerceptron() {
        Random rng = new Random();
        learningRate = 0.1f;
        //learningRate = 1f / (rng.nextInt(9) + 1);
    }
    
    public String doTraining(ArrayList<float[]> inputSequence, ArrayList<Float> targets){
        int totalInput = inputSequence.get(0).length;
        float weights[] = randomizeWeights(totalInput);
        String result = "Initial weights : ";
        int jumlahError = 0;
        int epoch = 10;
        boolean epochRate;
        
        result += Arrays.toString(weights) + "\n";
        result += "Initial learning rate : ";
        result += learningRate + "\n\n";
        for (int j=1; j<=epoch; j++){
            result += "\nEpoch ke - "+j+"\n";
            epochRate = true;
            for(int i = 0; i < inputSequence.size(); i++){
                float summation = calculateSummation(inputSequence.get(i), weights);
                float output = decideOutput(summation);
                float error = targets.get(i) - output;

                result += "Input (" + (i + 1) + ") : " + Arrays.toString(inputSequence.get(i))
                        + ", Output : " + output + ", Target : " + targets.get(i)
                        + "\n";
                if(error != 0){
                    epochRate = false;
                    updateWeights(inputSequence.get(i), weights, error);
                    result += "New weights : " + Arrays.toString(weights) + "\n";
                }
            }
            if(epochRate)
               break;
        }        
        result += "\nFinal Weights : " + Arrays.toString(weights) + "\n";
        result += doTesting(inputSequence, targets, weights);
        return result;
    }
    
    public String doTesting(ArrayList<float[]> inputSequence, ArrayList<Float> targets, float[] weights){
        int totalInput = inputSequence.get(0).length;
        String result = "\nHasil Testing :\n";
        int jumlahError = 0;
        
        for(int i = 0; i < inputSequence.size(); i++){
            float summation = calculateSummation(inputSequence.get(i), weights);
            float output = decideOutput(summation);
            float error = targets.get(i) - output;
            
            result += "Input (" + (i + 1) + ") : " + Arrays.toString(inputSequence.get(i))
                    + ", Output : " + output + ", Target : " + targets.get(i)
                    + "\n";
            if(error != 0){
                jumlahError++;
            }
        }
        result += "\nError Rasio   : " + ((float)jumlahError / inputSequence.size() * 100) + "%";
        return result;
    }
    
    public float decideOutput(float summation){
        final float THRESHOLD = 0;
        
        return summation < THRESHOLD ? 0f : 1f;
    };
    
    private void updateWeights(float[] inputs, float[] weights, float error){
        weights[0] = weights[0] + learningRate * BIAS * error;
        for(int i = 1; i < weights.length; i++){
            weights[i] = weights[i] + learningRate * inputs[i - 1] * error;
        }
    }
    
    private float calculateSummation(float[] inputs, float[] weights){
        float summation = BIAS * weights[0];
        
        for(int i = 1; i < weights.length; i++){
            summation += inputs[i - 1] * weights[i];
        }
        
        return summation;
    }
    
    private float[] randomizeWeights(int totalInput){
        Random rng = new Random();
        float[] randomizedWeights = new float[totalInput + 1];
        
        for(int i =  0; i < randomizedWeights.length; i++){
            //generate random between -1 and 1 with 1 number behind dot
            randomizedWeights[i] = (float) (Math.pow(-1f, rng.nextInt() % 2)
            // add 1 so it doesnt got divided by zero
                    / (rng.nextInt(9) + 1 )); 
        }
        
        return randomizedWeights;
    }
}
