package com.example.vancouvertourismappfinal.RestRoomFinderClasses.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class BathroomEntityDao extends AbstractDao<BathroomEntity, Long> {

    public static final String TABLENAME = "BATHROOM_ENTITY";

    /**
     * Properties of entity BathroomEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Street = new Property(2, String.class, "street", false, "STREET");
        public final static Property City = new Property(3, String.class, "city", false, "CITY");
        public final static Property State = new Property(4, String.class, "state", false, "STATE");
        public final static Property Country = new Property(5, String.class, "country", false, "COUNTRY");
        public final static Property Accessible = new Property(6, Boolean.class, "accessible", false, "ACCESSIBLE");
        public final static Property Unisex = new Property(7, Boolean.class, "unisex", false, "UNISEX");
        public final static Property Directions = new Property(8, String.class, "directions", false, "DIRECTIONS");
        public final static Property Comment = new Property(9, String.class, "comment", false, "COMMENT");
        public final static Property Upvote = new Property(10, Integer.class, "upvote", false, "UPVOTE");
        public final static Property Downvote = new Property(11, Integer.class, "downvote", false, "DOWNVOTE");
        public final static Property Latitude = new Property(12, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(13, Double.class, "longitude", false, "LONGITUDE");
        public final static Property Timestamp = new Property(14, Long.class, "timestamp", false, "TIMESTAMP");
    };


    public BathroomEntityDao(DaoConfig config) {
        super(config);
    }
    
    public BathroomEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BATHROOM_ENTITY\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"STREET\" TEXT," + // 2: street
                "\"CITY\" TEXT," + // 3: city
                "\"STATE\" TEXT," + // 4: state
                "\"COUNTRY\" TEXT," + // 5: country
                "\"ACCESSIBLE\" INTEGER," + // 6: accessible
                "\"UNISEX\" INTEGER," + // 7: unisex
                "\"DIRECTIONS\" TEXT," + // 8: directions
                "\"COMMENT\" TEXT," + // 9: comment
                "\"UPVOTE\" INTEGER," + // 10: upvote
                "\"DOWNVOTE\" INTEGER," + // 11: downvote
                "\"LATITUDE\" REAL," + // 12: latitude
                "\"LONGITUDE\" REAL," + // 13: longitude
                "\"TIMESTAMP\" INTEGER);"); // 14: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BATHROOM_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BathroomEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String street = entity.getStreet();
        if (street != null) {
            stmt.bindString(3, street);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(4, city);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(5, state);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(6, country);
        }
 
        Boolean accessible = entity.getAccessible();
        if (accessible != null) {
            stmt.bindLong(7, accessible ? 1L: 0L);
        }
 
        Boolean unisex = entity.getUnisex();
        if (unisex != null) {
            stmt.bindLong(8, unisex ? 1L: 0L);
        }
 
        String directions = entity.getDirections();
        if (directions != null) {
            stmt.bindString(9, directions);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(10, comment);
        }
 
        Integer upvote = entity.getUpvote();
        if (upvote != null) {
            stmt.bindLong(11, upvote);
        }
 
        Integer downvote = entity.getDownvote();
        if (downvote != null) {
            stmt.bindLong(12, downvote);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(13, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(14, longitude);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(15, timestamp);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public BathroomEntity readEntity(Cursor cursor, int offset) {
        BathroomEntity entity = new BathroomEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // street
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // city
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // state
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // country
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // accessible
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0, // unisex
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // directions
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // comment
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // upvote
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // downvote
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // latitude
            cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13), // longitude
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BathroomEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStreet(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCity(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setState(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCountry(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAccessible(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setUnisex(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
        entity.setDirections(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setComment(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setUpvote(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setDownvote(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setLatitude(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setLongitude(cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13));
        entity.setTimestamp(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(BathroomEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(BathroomEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
