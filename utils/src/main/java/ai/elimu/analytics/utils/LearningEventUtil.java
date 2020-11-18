package ai.elimu.analytics.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ai.elimu.model.enums.analytics.LearningEventType;
import ai.elimu.model.v2.gson.content.LetterGson;
import ai.elimu.model.v2.gson.content.StoryBookGson;
import ai.elimu.model.v2.gson.content.WordGson;

public class LearningEventUtil {

    public static void reportLetterLearningEvent(LetterGson letterGson, LearningEventType learningEventType, Context context, String analyticsApplicationId) {
        Log.i(LearningEventType.class.getName(), "reportLetterLearningEvent");

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("ai.elimu.intent.action.LETTER_LEARNING_EVENT");
        broadcastIntent.putExtra("packageName", context.getPackageName());
        broadcastIntent.putExtra("letterId", letterGson.getId());
        broadcastIntent.putExtra("letterText", letterGson.getText());
        broadcastIntent.putExtra("learningEventType", learningEventType.toString());
        broadcastIntent.setPackage(analyticsApplicationId);
        context.sendBroadcast(broadcastIntent);
    }

    public static void reportWordLearningEvent(WordGson wordGson, LearningEventType learningEventType, Context context, String analyticsApplicationId) {
        Log.i(LearningEventType.class.getName(), "reportWordLearningEvent");

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("ai.elimu.intent.action.WORD_LEARNING_EVENT");
        broadcastIntent.putExtra("packageName", context.getPackageName());
        broadcastIntent.putExtra("wordId", wordGson.getId());
        broadcastIntent.putExtra("wordText", wordGson.getText());
        broadcastIntent.putExtra("learningEventType", learningEventType.toString());
        broadcastIntent.setPackage(analyticsApplicationId);
        context.sendBroadcast(broadcastIntent);
    }

    public static void reportStoryBookLearningEvent(StoryBookGson storyBookGson, LearningEventType learningEventType, Context context, String analyticsApplicationId) {
        Log.i(LearningEventType.class.getName(), "reportStoryBookLearningEvent");

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("ai.elimu.intent.action.STORYBOOK_LEARNING_EVENT");
        broadcastIntent.putExtra("packageName", context.getPackageName());
        broadcastIntent.putExtra("storyBookId", storyBookGson.getId());
        broadcastIntent.putExtra("learningEventType", learningEventType.toString());
        broadcastIntent.setPackage(analyticsApplicationId);
        context.sendBroadcast(broadcastIntent);
    }
}
