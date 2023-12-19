package com.example.mnactecapp;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QuestionManager {
    private Set<Integer> usedIndices;
    private Random random;

    public QuestionManager() {
        usedIndices = new HashSet<>();
        random = new Random();
    }

    public int getRandomUnusedIndex(int totalQuestions) {
        if (usedIndices.size() == totalQuestions) {
            // Todos los índices ya se han utilizado
            return -1;
        }

        int randomIndex;
        do {
            randomIndex = random.nextInt(totalQuestions);
        } while (usedIndices.contains(randomIndex));

        usedIndices.add(randomIndex);
        return randomIndex;
    }

    public void resetUsedIndices() {
        usedIndices.clear();
    }
}