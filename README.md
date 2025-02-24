# elimu.ai Analytics 📊

Android application which collects, provides and uploads learning event data.

## Software Architecture

[
  <img width="320" alt="Software Architecture" src="https://user-images.githubusercontent.com/15718174/83595568-fb6a1e00-a594-11ea-990a-10c0bd62ed11.png">
](https://github.com/elimu-ai/wiki/blob/main/SOFTWARE_ARCHITECTURE.md)

## Learning Events

The various types of _learning events_ are handled by the Android receivers at [`app/src/main/java/ai/elimu/analytics/receiver`](app/src/main/java/ai/elimu/analytics/receiver).

A _learning event_ is an event without any corresponding testing of the student's mastery of a concept. For example; A student presses a word in a storybook and listens to its pronunciation.

  * `StoryBookLearningEvent`
  * `WordLearningEvent`
  * `LetterLearningEvent`

## Assessment Events

The various types of _assessment events_ are handled by the Android receivers at [`app/src/main/java/ai/elimu/analytics/receiver`](app/src/main/java/ai/elimu/analytics/receiver).

An _assessment event_ is an event that involves testing of whether or not the student is able to master a concept. For example; A word is sounded out, and the student is asked select the corresponding written text amongst a list of alternatives (multiple choice).

  * `WordAssessmentEvent`
  * `LetterAssessmentEvent`

## Development 👩🏽‍💻

Compile APK:

```
./gradlew clean build
```

Install APK:

```
adb install app/build/outputs/apk/debug/ai.elimu.analytics-<versionCode>-debug.apk
```

### Utils Library 📦

A [`utils`](utils) library (`.aar`) makes it easier for other Android apps to report learning/assessment events.

* [`LearningEventUtil`](https://github.com/elimu-ai/analytics/blob/main/utils/src/main/java/ai/elimu/analytics/utils/LearningEventUtil.java)
* [`AssessmentEventUtil`](https://github.com/elimu-ai/analytics/blob/main/utils/src/main/java/ai/elimu/analytics/utils/AssessmentEventUtil.java)

See https://jitpack.io/#elimu-ai/analytics/ for the latest version.

#### Utils Usage Sample

To use the `utils` library in another Android app, add the dependency in `app/build.gradle`:

```java
implementation 'com.github.elimu-ai:analytics:<version>@aar'
```

For an example of an app that is reporting learning events, see https://github.com/elimu-ai/vitabu:

  * https://github.com/elimu-ai/vitabu/blob/main/app/build.gradle#L51
  * https://github.com/elimu-ai/vitabu/blob/main/app/src/main/java/ai/elimu/vitabu/ui/storybook/ChapterFragment.java#L150

### Database Migration 🔀

When adding a new database `@Entity` (or modifying an existing one), you need to prepare a database 
migration (SQL script) in 
[`app/src/main/java/ai/elimu/analytics/db/RoomDb.java`](app/src/main/java/ai/elimu/analytics/db/RoomDb.java).

Follow these steps:

1. Add the new/modified `@Entity` to [`app/src/main/java/ai/elimu/analytics/entity/`](app/src/main/java/ai/elimu/analytics/entity/)
1. Add the entity's DAO interface to [`app/src/main/java/ai/elimu/analytics/dao/`](app/src/main/java/ai/elimu/analytics/dao/)
1. Include the DAO interface in [`app/src/main/java/ai/elimu/analytics/db/RoomDb.java`](app/src/main/java/ai/elimu/analytics/db/RoomDb.java)
1. Include the entity in the `entities` section of the `@Database` in [`app/src/main/java/ai/elimu/analytics/db/RoomDb.java`](app/src/main/java/ai/elimu/analytics/db/RoomDb.java)
1. Bump the `@Database` version in [`app/src/main/java/ai/elimu/analytics/db/RoomDb.java`](app/src/main/java/ai/elimu/analytics/db/RoomDb.java)
1. Build the code with `./gradlew clean build`
1. Open the new database schema generated at `app/schemas/ai.elimu.analytics.db.RoomDb/<version>.json`
  - Under `entities`, find the matching `tableName` and copy its SQL script from the `createSql` property.
1. Open `RoomDb.java` and add a new method for the latest migration
  - Paste the SQL script from the above JSON schema, and replace `${TABLE_NAME}` with the name of the table you created/modified.
  - Include the migration in the `getDatabase` method in `RoomDb.java`.
1. To run the database migration, launch the application on your device.

**Tip #1:** To verify that your database migration ran successfully, look at the Logcat output and 
ensure that there are no RoomDb errors:
```
2023-10-25 15:40:55.640 15303-914   RoomDb                  ai.elimu.analytics.debug             I  migrate (5 --> 6)
2023-10-25 15:40:55.641 15303-914   RoomDb                  ai.elimu.analytics.debug             I  sql: CREATE TABLE IF NOT EXISTS `LetterSoundCorrespondenceLearningEvent` (`letterSoundCorrespondenceLearningEventId` INTEGER, `androidId` TEXT NOT NULL, `packageName` TEXT NOT NULL, `time` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT)
```

**Tip #2:** You can also use Android Studio's _Database Inspector_ to verify that the database 
migration succeeded:

![](...)

---

elimu.ai - Free open source learning software for out-of-school children ✨🚀

[Website 🌐](https://elimu.ai) &nbsp; [Wiki 📃](https://github.com/elimu-ai/wiki#readme) &nbsp; [Projects 👩🏽‍💻](https://github.com/elimu-ai/wiki/projects) &nbsp; [Milestones 🎯](https://github.com/elimu-ai/wiki/milestones) &nbsp; [Community 👋🏽](https://github.com/elimu-ai/wiki#open-source-community)
