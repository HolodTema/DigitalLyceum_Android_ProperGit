package com.example.lyceumapp.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.lyceumapp.json.lessons.LessonEndTimeInterval;
import com.example.lyceumapp.json.lessons.LessonJson;
import com.example.lyceumapp.json.lessons.LessonStartTimeInterval;
import com.example.lyceumapp.json.teachers.TeacherJson;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LessonDao_Impl implements LessonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LessonJson> __insertionAdapterOfLessonJson;

  private final EntityDeletionOrUpdateAdapter<LessonJson> __deletionAdapterOfLessonJson;

  private final EntityDeletionOrUpdateAdapter<LessonJson> __updateAdapterOfLessonJson;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public LessonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLessonJson = new EntityInsertionAdapter<LessonJson>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LessonJson` (`lesson_id`,`lesson_name`,`week`,`weekday`,`startHour`,`startMinute`,`endHour`,`endMinute`,`teacher_id`,`teacher_name`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonJson value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getWeek());
        stmt.bindLong(4, value.getWeekday());
        final LessonStartTimeInterval _tmpStartTime = value.getStartTime();
        if(_tmpStartTime != null) {
          stmt.bindLong(5, _tmpStartTime.getStartHour());
          stmt.bindLong(6, _tmpStartTime.getStartMinute());
        } else {
          stmt.bindNull(5);
          stmt.bindNull(6);
        }
        final LessonEndTimeInterval _tmpEndTime = value.getEndTime();
        if(_tmpEndTime != null) {
          stmt.bindLong(7, _tmpEndTime.getEndHour());
          stmt.bindLong(8, _tmpEndTime.getEndMinute());
        } else {
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
        final TeacherJson _tmpTeacher = value.getTeacher();
        if(_tmpTeacher != null) {
          stmt.bindLong(9, _tmpTeacher.getId());
          if (_tmpTeacher.getName() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpTeacher.getName());
          }
        } else {
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
      }
    };
    this.__deletionAdapterOfLessonJson = new EntityDeletionOrUpdateAdapter<LessonJson>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `LessonJson` WHERE `lesson_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonJson value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfLessonJson = new EntityDeletionOrUpdateAdapter<LessonJson>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `LessonJson` SET `lesson_id` = ?,`lesson_name` = ?,`week` = ?,`weekday` = ?,`startHour` = ?,`startMinute` = ?,`endHour` = ?,`endMinute` = ?,`teacher_id` = ?,`teacher_name` = ? WHERE `lesson_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonJson value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getWeek());
        stmt.bindLong(4, value.getWeekday());
        final LessonStartTimeInterval _tmpStartTime = value.getStartTime();
        if(_tmpStartTime != null) {
          stmt.bindLong(5, _tmpStartTime.getStartHour());
          stmt.bindLong(6, _tmpStartTime.getStartMinute());
        } else {
          stmt.bindNull(5);
          stmt.bindNull(6);
        }
        final LessonEndTimeInterval _tmpEndTime = value.getEndTime();
        if(_tmpEndTime != null) {
          stmt.bindLong(7, _tmpEndTime.getEndHour());
          stmt.bindLong(8, _tmpEndTime.getEndMinute());
        } else {
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
        final TeacherJson _tmpTeacher = value.getTeacher();
        if(_tmpTeacher != null) {
          stmt.bindLong(9, _tmpTeacher.getId());
          if (_tmpTeacher.getName() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, _tmpTeacher.getName());
          }
        } else {
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
        stmt.bindLong(11, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from lessonjson";
        return _query;
      }
    };
  }

  @Override
  public void insert(final LessonJson lessonJson) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLessonJson.insert(lessonJson);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final LessonJson lessonJson) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfLessonJson.handle(lessonJson);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final LessonJson lessonJson) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLessonJson.handle(lessonJson);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public boolean areLessonsInDatabase() {
    final String _sql = "select exists(select * from lessonjson)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<LessonJson> getAll() {
    final String _sql = "select * from lessonjson";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_name");
      final int _cursorIndexOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "week");
      final int _cursorIndexOfWeekday = CursorUtil.getColumnIndexOrThrow(_cursor, "weekday");
      final int _cursorIndexOfStartHour = CursorUtil.getColumnIndexOrThrow(_cursor, "startHour");
      final int _cursorIndexOfStartMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "startMinute");
      final int _cursorIndexOfEndHour = CursorUtil.getColumnIndexOrThrow(_cursor, "endHour");
      final int _cursorIndexOfEndMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "endMinute");
      final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "teacher_id");
      final int _cursorIndexOfName_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "teacher_name");
      final List<LessonJson> _result = new ArrayList<LessonJson>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LessonJson _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpWeek;
        _tmpWeek = _cursor.getInt(_cursorIndexOfWeek);
        final int _tmpWeekday;
        _tmpWeekday = _cursor.getInt(_cursorIndexOfWeekday);
        final LessonStartTimeInterval _tmpStartTime;
        if (! (_cursor.isNull(_cursorIndexOfStartHour) && _cursor.isNull(_cursorIndexOfStartMinute))) {
          final int _tmpStartHour;
          _tmpStartHour = _cursor.getInt(_cursorIndexOfStartHour);
          final int _tmpStartMinute;
          _tmpStartMinute = _cursor.getInt(_cursorIndexOfStartMinute);
          _tmpStartTime = new LessonStartTimeInterval(_tmpStartHour,_tmpStartMinute);
        }  else  {
          _tmpStartTime = null;
        }
        final LessonEndTimeInterval _tmpEndTime;
        if (! (_cursor.isNull(_cursorIndexOfEndHour) && _cursor.isNull(_cursorIndexOfEndMinute))) {
          final int _tmpEndHour;
          _tmpEndHour = _cursor.getInt(_cursorIndexOfEndHour);
          final int _tmpEndMinute;
          _tmpEndMinute = _cursor.getInt(_cursorIndexOfEndMinute);
          _tmpEndTime = new LessonEndTimeInterval(_tmpEndHour,_tmpEndMinute);
        }  else  {
          _tmpEndTime = null;
        }
        final TeacherJson _tmpTeacher;
        if (! (_cursor.isNull(_cursorIndexOfId_1) && _cursor.isNull(_cursorIndexOfName_1))) {
          final int _tmpId_1;
          _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
          final String _tmpName_1;
          if (_cursor.isNull(_cursorIndexOfName_1)) {
            _tmpName_1 = null;
          } else {
            _tmpName_1 = _cursor.getString(_cursorIndexOfName_1);
          }
          _tmpTeacher = new TeacherJson(_tmpId_1,_tmpName_1);
        }  else  {
          _tmpTeacher = null;
        }
        _item = new LessonJson(_tmpId,_tmpName,_tmpStartTime,_tmpEndTime,_tmpWeek,_tmpWeekday,_tmpTeacher);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
