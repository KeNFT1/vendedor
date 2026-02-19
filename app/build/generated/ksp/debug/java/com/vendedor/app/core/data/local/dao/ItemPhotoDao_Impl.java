package com.vendedor.app.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemPhotoDao_Impl implements ItemPhotoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemPhotoEntity> __insertionAdapterOfItemPhotoEntity;

  private final EntityDeletionOrUpdateAdapter<ItemPhotoEntity> __deletionAdapterOfItemPhotoEntity;

  private final EntityDeletionOrUpdateAdapter<ItemPhotoEntity> __updateAdapterOfItemPhotoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForItem;

  private final SharedSQLiteStatement __preparedStmtOfClearPrimaryForItem;

  private final SharedSQLiteStatement __preparedStmtOfSetPrimary;

  public ItemPhotoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemPhotoEntity = new EntityInsertionAdapter<ItemPhotoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `item_photos` (`id`,`itemId`,`filePath`,`isPrimary`,`sortOrder`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemPhotoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        statement.bindString(3, entity.getFilePath());
        final int _tmp = entity.isPrimary() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getSortOrder());
        statement.bindLong(6, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfItemPhotoEntity = new EntityDeletionOrUpdateAdapter<ItemPhotoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `item_photos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemPhotoEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfItemPhotoEntity = new EntityDeletionOrUpdateAdapter<ItemPhotoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `item_photos` SET `id` = ?,`itemId` = ?,`filePath` = ?,`isPrimary` = ?,`sortOrder` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemPhotoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        statement.bindString(3, entity.getFilePath());
        final int _tmp = entity.isPrimary() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getSortOrder());
        statement.bindLong(6, entity.getCreatedAt());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllForItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM item_photos WHERE itemId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearPrimaryForItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE item_photos SET isPrimary = 0 WHERE itemId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetPrimary = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE item_photos SET isPrimary = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ItemPhotoEntity photo, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfItemPhotoEntity.insertAndReturnId(photo);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ItemPhotoEntity photo, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfItemPhotoEntity.handle(photo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ItemPhotoEntity photo, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfItemPhotoEntity.handle(photo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForItem(final long itemId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllForItem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearPrimaryForItem(final long itemId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearPrimaryForItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearPrimaryForItem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setPrimary(final long photoId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetPrimary.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, photoId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetPrimary.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ItemPhotoEntity>> getPhotosForItem(final long itemId) {
    final String _sql = "SELECT * FROM item_photos WHERE itemId = ? ORDER BY sortOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"item_photos"}, new Callable<List<ItemPhotoEntity>>() {
      @Override
      @NonNull
      public List<ItemPhotoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfIsPrimary = CursorUtil.getColumnIndexOrThrow(_cursor, "isPrimary");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ItemPhotoEntity> _result = new ArrayList<ItemPhotoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemPhotoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpFilePath;
            _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            final boolean _tmpIsPrimary;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPrimary);
            _tmpIsPrimary = _tmp != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ItemPhotoEntity(_tmpId,_tmpItemId,_tmpFilePath,_tmpIsPrimary,_tmpSortOrder,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getPrimaryPhoto(final long itemId,
      final Continuation<? super ItemPhotoEntity> $completion) {
    final String _sql = "SELECT * FROM item_photos WHERE itemId = ? AND isPrimary = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ItemPhotoEntity>() {
      @Override
      @Nullable
      public ItemPhotoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfIsPrimary = CursorUtil.getColumnIndexOrThrow(_cursor, "isPrimary");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ItemPhotoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpFilePath;
            _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            final boolean _tmpIsPrimary;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPrimary);
            _tmpIsPrimary = _tmp != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ItemPhotoEntity(_tmpId,_tmpItemId,_tmpFilePath,_tmpIsPrimary,_tmpSortOrder,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
