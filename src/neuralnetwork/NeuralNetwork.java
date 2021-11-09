/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import model.Dataset;
import model.LineOfData;
import model.SinglePerceptron;
import model.SinglePerceptronHeart;

/**
 *
 * @author asus
 */
public class NeuralNetwork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dataset dataset = new Dataset("resource/heart.csv");
        ArrayList<LineOfData> data = dataset.getDatas();
        ArrayList<float[]> inputSequence = new ArrayList<float[]>();
        ArrayList<Float> targets = new ArrayList<Float>();
        SinglePerceptronHeart sPercep = new SinglePerceptronHeart();
        
        for(int i = 0; i < data.size(); i++){
            float[] inputCandidates = new float[data.get(i).getAttributes().length];
            
            for(int j = 0; j < inputCandidates.length; j++){
                inputCandidates[j] = Float.parseFloat(data.get(i).getAttributes()[j]);
            }
            inputSequence.add(inputCandidates);
            targets.add(Float.parseFloat(data.get(i).getClassLabel()));
        }
        System.out.println("Fungsi Heart");
        System.out.println(sPercep.doTraining(inputSequence, targets));
    }
}
