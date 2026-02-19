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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vendedor.app.core.data.local.entity.ListingEntity;
import java.lang.Class;
import java.lang.Double;
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
public final class ListingDao_Impl implements ListingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ListingEntity> __insertionAdapterOfListingEntity;

  private final EntityDeletionOrUpdateAdapter<ListingEntity> __deletionAdapterOfListingEntity;

  private final EntityDeletionOrUpdateAdapter<ListingEntity> __updateAdapterOfListingEntity;

  public ListingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListingEntity = new EntityInsertionAdapter<ListingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `listings` (`id`,`itemId`,`marketplace`,`externalListingId`,`listingUrl`,`status`,`listedPrice`,`listedAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ListingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        statement.bindString(3, entity.getMarketplace());
        if (entity.getExternalListingId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExternalListingId());
        }
        if (entity.getListingUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getListingUrl());
        }
        statement.bindString(6, entity.getStatus());
        if (entity.getListedPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getListedPrice());
        }
        if (entity.getListedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getListedAt());
        }
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfListingEntity = new EntityDeletionOrUpdateAdapter<ListingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `listings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ListingEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfListingEntity = new EntityDeletionOrUpdateAdapter<ListingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `listings` SET `id` = ?,`itemId` = ?,`marketplace` = ?,`externalListingId` = ?,`listingUrl` = ?,`status` = ?,`listedPrice` = ?,`listedAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ListingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        statement.bindString(3, entity.getMarketplace());
        if (entity.getExternalListingId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getExternalListingId());
        }
        if (entity.getListingUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getListingUrl());
        }
        statement.bindString(6, entity.getStatus());
        if (entity.getListedPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getListedPrice());
        }
        if (entity.getListedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getListedAt());
        }
        statement.bindLong(9, entity.getUpdatedAt());
        statement.bindLong(10, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final ListingEntity listing, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfListingEntity.insertAndReturnId(listing);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ListingEntity listing, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfListingEntity.handle(listing);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ListingEntity listing, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfListingEntity.handle(listing);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ListingEntity>> getListingsForItem(final long itemId) {
    final String _sql = "SELECT * FROM listings WHERE itemId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"listings"}, new Callable<List<ListingEntity>>() {
      @Override
      @NonNull
      public List<ListingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfMarketplace = CursorUtil.getColumnIndexOrThrow(_cursor, "marketplace");
          final int _cursorIndexOfExternalListingId = CursorUtil.getColumnIndexOrThrow(_cursor, "externalListingId");
          final int _cursorIndexOfListingUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "listingUrl");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfListedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "listedPrice");
          final int _cursorIndexOfListedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "listedAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ListingEntity> _result = new ArrayList<ListingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ListingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpMarketplace;
            _tmpMarketplace = _cursor.getString(_cursorIndexOfMarketplace);
            final String _tmpExternalListingId;
            if (_cursor.isNull(_cursorIndexOfExternalListingId)) {
              _tmpExternalListingId = null;
            } else {
              _tmpExternalListingId = _cursor.getString(_cursorIndexOfExternalListingId);
            }
            final String _tmpListingUrl;
            if (_cursor.isNull(_cursorIndexOfListingUrl)) {
              _tmpListingUrl = null;
            } else {
              _tmpListingUrl = _cursor.getString(_cursorIndexOfListingUrl);
            }
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Double _tmpListedPrice;
            if (_cursor.isNull(_cursorIndexOfListedPrice)) {
              _tmpListedPrice = null;
            } else {
              _tmpListedPrice = _cursor.getDouble(_cursorIndexOfListedPrice);
            }
            final Long _tmpListedAt;
            if (_cursor.isNull(_cursorIndexOfListedAt)) {
              _tmpListedAt = null;
            } else {
              _tmpListedAt = _cursor.getLong(_cursorIndexOfListedAt);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ListingEntity(_tmpId,_tmpItemId,_tmpMarketplace,_tmpExternalListingId,_tmpListingUrl,_tmpStatus,_tmpListedPrice,_tmpListedAt,_tmpUpdatedAt);
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
  public Object getListingById(final long id,
      final Continuation<? super ListingEntity> $completion) {
    final String _sql = "SELECT * FROM listings WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ListingEntity>() {
      @Override
      @Nullable
      public ListingEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfMarketplace = CursorUtil.getColumnIndexOrThrow(_cursor, "marketplace");
          final int _cursorIndexOfExternalListingId = CursorUtil.getColumnIndexOrThrow(_cursor, "externalListingId");
          final int _cursorIndexOfListingUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "listingUrl");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfListedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "listedPrice");
          final int _cursorIndexOfListedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "listedAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ListingEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpMarketplace;
            _tmpMarketplace = _cursor.getString(_cursorIndexOfMarketplace);
            final String _tmpExternalListingId;
            if (_cursor.isNull(_cursorIndexOfExternalListingId)) {
              _tmpExternalListingId = null;
            } else {
              _tmpExternalListingId = _cursor.getString(_cursorIndexOfExternalListingId);
            }
            final String _tmpListingUrl;
            if (_cursor.isNull(_cursorIndexOfListingUrl)) {
              _tmpListingUrl = null;
            } else {
              _tmpListingUrl = _cursor.getString(_cursorIndexOfListingUrl);
            }
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final Double _tmpListedPrice;
            if (_cursor.isNull(_cursorIndexOfListedPrice)) {
              _tmpListedPrice = null;
            } else {
              _tmpListedPrice = _cursor.getDouble(_cursorIndexOfListedPrice);
            }
            final Long _tmpListedAt;
            if (_cursor.isNull(_cursorIndexOfListedAt)) {
              _tmpListedAt = null;
            } else {
              _tmpListedAt = _cursor.getLong(_cursorIndexOfListedAt);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ListingEntity(_tmpId,_tmpItemId,_tmpMarketplace,_tmpExternalListingId,_tmpListingUrl,_tmpStatus,_tmpListedPrice,_tmpListedAt,_tmpUpdatedAt);
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
