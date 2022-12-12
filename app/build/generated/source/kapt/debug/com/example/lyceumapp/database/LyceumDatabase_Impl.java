package com.example.lyceumapp.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LyceumDatabase_Impl extends LyceumDatabase {
  private volatile LessonDao _lessonDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LessonJson` (`lesson_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `lesson_name` TEXT NOT NULL, `week` INTEGER NOT NULL, `weekday` INTEGER NOT NULL, `startHour` INTEGER NOT NULL, `startMinute` INTEGER NOT NULL, `endHour` INTEGER NOT NULL, `endMinute` INTEGER NOT NULL, `teacher_id` INTEGER NOT NULL, `teacher_name` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd7f0bc643eddb1742e6287b53f26a1b5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `LessonJson`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsLessonJson = new HashMap<String, TableInfo.Column>(10);
        _columnsLessonJson.put("lesson_id", new TableInfo.Column("lesson_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("lesson_name", new TableInfo.Column("lesson_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("week", new TableInfo.Column("week", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("weekday", new TableInfo.Column("weekday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("startHour", new TableInfo.Column("startHour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("startMinute", new TableInfo.Column("startMinute", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("endHour", new TableInfo.Column("endHour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("endMinute", new TableInfo.Column("endMinute", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("teacher_id", new TableInfo.Column("teacher_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonJson.put("teacher_name", new TableInfo.Column("teacher_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLessonJson = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLessonJson = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLessonJson = new TableInfo("LessonJson", _columnsLessonJson, _foreignKeysLessonJson, _indicesLessonJson);
        final TableInfo _existingLessonJson = TableInfo.read(_db, "LessonJson");
        if (! _infoLessonJson.equals(_existingLessonJson)) {
          return new RoomOpenHelper.ValidationResult(false, "LessonJson(com.example.lyceumapp.json.lessons.LessonJson).\n"
                  + " Expected:\n" + _infoLessonJson + "\n"
                  + " Found:\n" + _existingLessonJson);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d7f0bc643eddb1742e6287b53f26a1b5", "5dd55d93970a00df4a001e5e74224ab8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "LessonJson");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `LessonJson`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LessonDao.class, LessonDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public LessonDao lessonDao() {
    if (_lessonDao != null) {
      return _lessonDao;
    } else {
      synchronized(this) {
        if(_lessonDao == null) {
          _lessonDao = new LessonDao_Impl(this);
        }
        return _lessonDao;
      }
    }
  }
}
