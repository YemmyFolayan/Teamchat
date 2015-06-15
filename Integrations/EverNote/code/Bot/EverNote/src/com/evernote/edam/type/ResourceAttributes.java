/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.evernote.edam.type;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import com.evernote.thrift.*;
import com.evernote.thrift.protocol.*;

/**
 * Structure holding the optional attributes of a Resource
 * <dl>
 * <dt>sourceURL</dt>
 *   <dd>the original location where the resource was hosted
 *   <br/>
 *    Length:  EDAM_ATTRIBUTE_LEN_MIN - EDAM_ATTRIBUTE_LEN_MAX
 *   </dd>
 * 
 * <dt>timestamp</dt>
 *   <dd>the date and time that is associated with this resource
 *   (e.g. the time embedded in an image from a digital camera with a clock)
 *   </dd>
 * 
 * <dt>latitude</dt>
 *   <dd>the latitude where the resource was captured
 *   </dd>
 * 
 * <dt>longitude</dt>
 *   <dd>the longitude where the resource was captured
 *   </dd>
 * 
 * <dt>altitude</dt>
 *   <dd>the altitude where the resource was captured
 *   </dd>
 * 
 * <dt>cameraMake</dt>
 *   <dd>information about an image's camera, e.g. as embedded in
 *   the image's EXIF data
 *   <br/>
 *   Length:  EDAM_ATTRIBUTE_LEN_MIN - EDAM_ATTRIBUTE_LEN_MAX
 *   </dd>
 * 
 * <dt>cameraModel</dt>
 *   <dd>information about an image's camera, e.g. as embedded
 *   in the image's EXIF data
 *   <br/>
 *   Length:  EDAM_ATTRIBUTE_LEN_MIN - EDAM_ATTRIBUTE_LEN_MAX
 *   </dd>
 * 
 * <dt>clientWillIndex</dt>
 *   <dd>if true, then the original client that submitted
 *   the resource plans to submit the recognition index for this resource at a
 *   later time.
 *   </dd>
 * 
 * <dt>recoType</dt>
 *   <dd>DEPRECATED - this field is no longer set by the service, so should
 *     be ignored.
 *   </dd>
 * 
 * <dt>fileName</dt>
 *   <dd>if the resource came from a source that provided an
 *   explicit file name, the original name will be stored here.  Many resources
 *   come from unnamed sources, so this will not always be set.
 *   </dd>
 * 
 * <dt>attachment</dt>
 *   <dd>this will be true if the resource should be displayed as an attachment,
 *   or false if the resource should be displayed inline (if possible).
 *   </dd>
 * 
 * <dt>applicationData</dt>
 * <dd>Provides a location for applications to store a relatively small
 * (4kb) blob of data associated with a Resource that is not visible to the user
 * and that is opaque to the Evernote service. A single application may use at most
 * one entry in this map, using its API consumer key as the map key. See the
 * documentation for LazyMap for a description of when the actual map values
 * are returned by the service.
 * <p>To safely add or modify your application's entry in the map, use
 * NoteStore.setResourceApplicationDataEntry. To safely remove your application's
 * entry from the map, use NoteStore.unsetResourceApplicationDataEntry.</p>
 * Minimum length of a name (key): EDAM_APPLICATIONDATA_NAME_LEN_MIN
 * <br/>
 * Sum max size of key and value: EDAM_APPLICATIONDATA_ENTRY_LEN_MAX
 * <br/>
 * Syntax regex for name (key): EDAM_APPLICATIONDATA_NAME_REGEX
 * </dd>
 * 
 * </dl>
 */
public class ResourceAttributes implements TBase<ResourceAttributes>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("ResourceAttributes");

  private static final TField SOURCE_URL_FIELD_DESC = new TField("sourceURL", TType.STRING, (short)1);
  private static final TField TIMESTAMP_FIELD_DESC = new TField("timestamp", TType.I64, (short)2);
  private static final TField LATITUDE_FIELD_DESC = new TField("latitude", TType.DOUBLE, (short)3);
  private static final TField LONGITUDE_FIELD_DESC = new TField("longitude", TType.DOUBLE, (short)4);
  private static final TField ALTITUDE_FIELD_DESC = new TField("altitude", TType.DOUBLE, (short)5);
  private static final TField CAMERA_MAKE_FIELD_DESC = new TField("cameraMake", TType.STRING, (short)6);
  private static final TField CAMERA_MODEL_FIELD_DESC = new TField("cameraModel", TType.STRING, (short)7);
  private static final TField CLIENT_WILL_INDEX_FIELD_DESC = new TField("clientWillIndex", TType.BOOL, (short)8);
  private static final TField RECO_TYPE_FIELD_DESC = new TField("recoType", TType.STRING, (short)9);
  private static final TField FILE_NAME_FIELD_DESC = new TField("fileName", TType.STRING, (short)10);
  private static final TField ATTACHMENT_FIELD_DESC = new TField("attachment", TType.BOOL, (short)11);
  private static final TField APPLICATION_DATA_FIELD_DESC = new TField("applicationData", TType.STRUCT, (short)12);

  private String sourceURL;
  private long timestamp;
  private double latitude;
  private double longitude;
  private double altitude;
  private String cameraMake;
  private String cameraModel;
  private boolean clientWillIndex;
  private String recoType;
  private String fileName;
  private boolean attachment;
  private LazyMap applicationData;


  // isset id assignments
  private static final int __TIMESTAMP_ISSET_ID = 0;
  private static final int __LATITUDE_ISSET_ID = 1;
  private static final int __LONGITUDE_ISSET_ID = 2;
  private static final int __ALTITUDE_ISSET_ID = 3;
  private static final int __CLIENTWILLINDEX_ISSET_ID = 4;
  private static final int __ATTACHMENT_ISSET_ID = 5;
  private boolean[] __isset_vector = new boolean[6];

  public ResourceAttributes() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ResourceAttributes(ResourceAttributes other) {
    System.arraycopy(other.__isset_vector, 0, __isset_vector, 0, other.__isset_vector.length);
    if (other.isSetSourceURL()) {
      this.sourceURL = other.sourceURL;
    }
    this.timestamp = other.timestamp;
    this.latitude = other.latitude;
    this.longitude = other.longitude;
    this.altitude = other.altitude;
    if (other.isSetCameraMake()) {
      this.cameraMake = other.cameraMake;
    }
    if (other.isSetCameraModel()) {
      this.cameraModel = other.cameraModel;
    }
    this.clientWillIndex = other.clientWillIndex;
    if (other.isSetRecoType()) {
      this.recoType = other.recoType;
    }
    if (other.isSetFileName()) {
      this.fileName = other.fileName;
    }
    this.attachment = other.attachment;
    if (other.isSetApplicationData()) {
      this.applicationData = new LazyMap(other.applicationData);
    }
  }

  public ResourceAttributes deepCopy() {
    return new ResourceAttributes(this);
  }

  public void clear() {
    this.sourceURL = null;
    setTimestampIsSet(false);
    this.timestamp = 0;
    setLatitudeIsSet(false);
    this.latitude = 0.0;
    setLongitudeIsSet(false);
    this.longitude = 0.0;
    setAltitudeIsSet(false);
    this.altitude = 0.0;
    this.cameraMake = null;
    this.cameraModel = null;
    setClientWillIndexIsSet(false);
    this.clientWillIndex = false;
    this.recoType = null;
    this.fileName = null;
    setAttachmentIsSet(false);
    this.attachment = false;
    this.applicationData = null;
  }

  public String getSourceURL() {
    return this.sourceURL;
  }

  public void setSourceURL(String sourceURL) {
    this.sourceURL = sourceURL;
  }

  public void unsetSourceURL() {
    this.sourceURL = null;
  }

  /** Returns true if field sourceURL is set (has been asigned a value) and false otherwise */
  public boolean isSetSourceURL() {
    return this.sourceURL != null;
  }

  public void setSourceURLIsSet(boolean value) {
    if (!value) {
      this.sourceURL = null;
    }
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
  }

  public void unsetTimestamp() {
    __isset_vector[__TIMESTAMP_ISSET_ID] = false;
  }

  /** Returns true if field timestamp is set (has been asigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return __isset_vector[__TIMESTAMP_ISSET_ID];
  }

  public void setTimestampIsSet(boolean value) {
    __isset_vector[__TIMESTAMP_ISSET_ID] = value;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
    setLatitudeIsSet(true);
  }

  public void unsetLatitude() {
    __isset_vector[__LATITUDE_ISSET_ID] = false;
  }

  /** Returns true if field latitude is set (has been asigned a value) and false otherwise */
  public boolean isSetLatitude() {
    return __isset_vector[__LATITUDE_ISSET_ID];
  }

  public void setLatitudeIsSet(boolean value) {
    __isset_vector[__LATITUDE_ISSET_ID] = value;
  }

  public double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
    setLongitudeIsSet(true);
  }

  public void unsetLongitude() {
    __isset_vector[__LONGITUDE_ISSET_ID] = false;
  }

  /** Returns true if field longitude is set (has been asigned a value) and false otherwise */
  public boolean isSetLongitude() {
    return __isset_vector[__LONGITUDE_ISSET_ID];
  }

  public void setLongitudeIsSet(boolean value) {
    __isset_vector[__LONGITUDE_ISSET_ID] = value;
  }

  public double getAltitude() {
    return this.altitude;
  }

  public void setAltitude(double altitude) {
    this.altitude = altitude;
    setAltitudeIsSet(true);
  }

  public void unsetAltitude() {
    __isset_vector[__ALTITUDE_ISSET_ID] = false;
  }

  /** Returns true if field altitude is set (has been asigned a value) and false otherwise */
  public boolean isSetAltitude() {
    return __isset_vector[__ALTITUDE_ISSET_ID];
  }

  public void setAltitudeIsSet(boolean value) {
    __isset_vector[__ALTITUDE_ISSET_ID] = value;
  }

  public String getCameraMake() {
    return this.cameraMake;
  }

  public void setCameraMake(String cameraMake) {
    this.cameraMake = cameraMake;
  }

  public void unsetCameraMake() {
    this.cameraMake = null;
  }

  /** Returns true if field cameraMake is set (has been asigned a value) and false otherwise */
  public boolean isSetCameraMake() {
    return this.cameraMake != null;
  }

  public void setCameraMakeIsSet(boolean value) {
    if (!value) {
      this.cameraMake = null;
    }
  }

  public String getCameraModel() {
    return this.cameraModel;
  }

  public void setCameraModel(String cameraModel) {
    this.cameraModel = cameraModel;
  }

  public void unsetCameraModel() {
    this.cameraModel = null;
  }

  /** Returns true if field cameraModel is set (has been asigned a value) and false otherwise */
  public boolean isSetCameraModel() {
    return this.cameraModel != null;
  }

  public void setCameraModelIsSet(boolean value) {
    if (!value) {
      this.cameraModel = null;
    }
  }

  public boolean isClientWillIndex() {
    return this.clientWillIndex;
  }

  public void setClientWillIndex(boolean clientWillIndex) {
    this.clientWillIndex = clientWillIndex;
    setClientWillIndexIsSet(true);
  }

  public void unsetClientWillIndex() {
    __isset_vector[__CLIENTWILLINDEX_ISSET_ID] = false;
  }

  /** Returns true if field clientWillIndex is set (has been asigned a value) and false otherwise */
  public boolean isSetClientWillIndex() {
    return __isset_vector[__CLIENTWILLINDEX_ISSET_ID];
  }

  public void setClientWillIndexIsSet(boolean value) {
    __isset_vector[__CLIENTWILLINDEX_ISSET_ID] = value;
  }

  public String getRecoType() {
    return this.recoType;
  }

  public void setRecoType(String recoType) {
    this.recoType = recoType;
  }

  public void unsetRecoType() {
    this.recoType = null;
  }

  /** Returns true if field recoType is set (has been asigned a value) and false otherwise */
  public boolean isSetRecoType() {
    return this.recoType != null;
  }

  public void setRecoTypeIsSet(boolean value) {
    if (!value) {
      this.recoType = null;
    }
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void unsetFileName() {
    this.fileName = null;
  }

  /** Returns true if field fileName is set (has been asigned a value) and false otherwise */
  public boolean isSetFileName() {
    return this.fileName != null;
  }

  public void setFileNameIsSet(boolean value) {
    if (!value) {
      this.fileName = null;
    }
  }

  public boolean isAttachment() {
    return this.attachment;
  }

  public void setAttachment(boolean attachment) {
    this.attachment = attachment;
    setAttachmentIsSet(true);
  }

  public void unsetAttachment() {
    __isset_vector[__ATTACHMENT_ISSET_ID] = false;
  }

  /** Returns true if field attachment is set (has been asigned a value) and false otherwise */
  public boolean isSetAttachment() {
    return __isset_vector[__ATTACHMENT_ISSET_ID];
  }

  public void setAttachmentIsSet(boolean value) {
    __isset_vector[__ATTACHMENT_ISSET_ID] = value;
  }

  public LazyMap getApplicationData() {
    return this.applicationData;
  }

  public void setApplicationData(LazyMap applicationData) {
    this.applicationData = applicationData;
  }

  public void unsetApplicationData() {
    this.applicationData = null;
  }

  /** Returns true if field applicationData is set (has been asigned a value) and false otherwise */
  public boolean isSetApplicationData() {
    return this.applicationData != null;
  }

  public void setApplicationDataIsSet(boolean value) {
    if (!value) {
      this.applicationData = null;
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ResourceAttributes)
      return this.equals((ResourceAttributes)that);
    return false;
  }

  public boolean equals(ResourceAttributes that) {
    if (that == null)
      return false;

    boolean this_present_sourceURL = true && this.isSetSourceURL();
    boolean that_present_sourceURL = true && that.isSetSourceURL();
    if (this_present_sourceURL || that_present_sourceURL) {
      if (!(this_present_sourceURL && that_present_sourceURL))
        return false;
      if (!this.sourceURL.equals(that.sourceURL))
        return false;
    }

    boolean this_present_timestamp = true && this.isSetTimestamp();
    boolean that_present_timestamp = true && that.isSetTimestamp();
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_latitude = true && this.isSetLatitude();
    boolean that_present_latitude = true && that.isSetLatitude();
    if (this_present_latitude || that_present_latitude) {
      if (!(this_present_latitude && that_present_latitude))
        return false;
      if (this.latitude != that.latitude)
        return false;
    }

    boolean this_present_longitude = true && this.isSetLongitude();
    boolean that_present_longitude = true && that.isSetLongitude();
    if (this_present_longitude || that_present_longitude) {
      if (!(this_present_longitude && that_present_longitude))
        return false;
      if (this.longitude != that.longitude)
        return false;
    }

    boolean this_present_altitude = true && this.isSetAltitude();
    boolean that_present_altitude = true && that.isSetAltitude();
    if (this_present_altitude || that_present_altitude) {
      if (!(this_present_altitude && that_present_altitude))
        return false;
      if (this.altitude != that.altitude)
        return false;
    }

    boolean this_present_cameraMake = true && this.isSetCameraMake();
    boolean that_present_cameraMake = true && that.isSetCameraMake();
    if (this_present_cameraMake || that_present_cameraMake) {
      if (!(this_present_cameraMake && that_present_cameraMake))
        return false;
      if (!this.cameraMake.equals(that.cameraMake))
        return false;
    }

    boolean this_present_cameraModel = true && this.isSetCameraModel();
    boolean that_present_cameraModel = true && that.isSetCameraModel();
    if (this_present_cameraModel || that_present_cameraModel) {
      if (!(this_present_cameraModel && that_present_cameraModel))
        return false;
      if (!this.cameraModel.equals(that.cameraModel))
        return false;
    }

    boolean this_present_clientWillIndex = true && this.isSetClientWillIndex();
    boolean that_present_clientWillIndex = true && that.isSetClientWillIndex();
    if (this_present_clientWillIndex || that_present_clientWillIndex) {
      if (!(this_present_clientWillIndex && that_present_clientWillIndex))
        return false;
      if (this.clientWillIndex != that.clientWillIndex)
        return false;
    }

    boolean this_present_recoType = true && this.isSetRecoType();
    boolean that_present_recoType = true && that.isSetRecoType();
    if (this_present_recoType || that_present_recoType) {
      if (!(this_present_recoType && that_present_recoType))
        return false;
      if (!this.recoType.equals(that.recoType))
        return false;
    }

    boolean this_present_fileName = true && this.isSetFileName();
    boolean that_present_fileName = true && that.isSetFileName();
    if (this_present_fileName || that_present_fileName) {
      if (!(this_present_fileName && that_present_fileName))
        return false;
      if (!this.fileName.equals(that.fileName))
        return false;
    }

    boolean this_present_attachment = true && this.isSetAttachment();
    boolean that_present_attachment = true && that.isSetAttachment();
    if (this_present_attachment || that_present_attachment) {
      if (!(this_present_attachment && that_present_attachment))
        return false;
      if (this.attachment != that.attachment)
        return false;
    }

    boolean this_present_applicationData = true && this.isSetApplicationData();
    boolean that_present_applicationData = true && that.isSetApplicationData();
    if (this_present_applicationData || that_present_applicationData) {
      if (!(this_present_applicationData && that_present_applicationData))
        return false;
      if (!this.applicationData.equals(that.applicationData))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ResourceAttributes other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ResourceAttributes typedOther = (ResourceAttributes)other;

    lastComparison = Boolean.valueOf(isSetSourceURL()).compareTo(typedOther.isSetSourceURL());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSourceURL()) {      lastComparison = TBaseHelper.compareTo(this.sourceURL, typedOther.sourceURL);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(typedOther.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {      lastComparison = TBaseHelper.compareTo(this.timestamp, typedOther.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLatitude()).compareTo(typedOther.isSetLatitude());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLatitude()) {      lastComparison = TBaseHelper.compareTo(this.latitude, typedOther.latitude);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLongitude()).compareTo(typedOther.isSetLongitude());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLongitude()) {      lastComparison = TBaseHelper.compareTo(this.longitude, typedOther.longitude);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAltitude()).compareTo(typedOther.isSetAltitude());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAltitude()) {      lastComparison = TBaseHelper.compareTo(this.altitude, typedOther.altitude);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCameraMake()).compareTo(typedOther.isSetCameraMake());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCameraMake()) {      lastComparison = TBaseHelper.compareTo(this.cameraMake, typedOther.cameraMake);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCameraModel()).compareTo(typedOther.isSetCameraModel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCameraModel()) {      lastComparison = TBaseHelper.compareTo(this.cameraModel, typedOther.cameraModel);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClientWillIndex()).compareTo(typedOther.isSetClientWillIndex());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClientWillIndex()) {      lastComparison = TBaseHelper.compareTo(this.clientWillIndex, typedOther.clientWillIndex);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRecoType()).compareTo(typedOther.isSetRecoType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRecoType()) {      lastComparison = TBaseHelper.compareTo(this.recoType, typedOther.recoType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFileName()).compareTo(typedOther.isSetFileName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileName()) {      lastComparison = TBaseHelper.compareTo(this.fileName, typedOther.fileName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAttachment()).compareTo(typedOther.isSetAttachment());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAttachment()) {      lastComparison = TBaseHelper.compareTo(this.attachment, typedOther.attachment);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetApplicationData()).compareTo(typedOther.isSetApplicationData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApplicationData()) {      lastComparison = TBaseHelper.compareTo(this.applicationData, typedOther.applicationData);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // SOURCE_URL
          if (field.type == TType.STRING) {
            this.sourceURL = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // TIMESTAMP
          if (field.type == TType.I64) {
            this.timestamp = iprot.readI64();
            setTimestampIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // LATITUDE
          if (field.type == TType.DOUBLE) {
            this.latitude = iprot.readDouble();
            setLatitudeIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // LONGITUDE
          if (field.type == TType.DOUBLE) {
            this.longitude = iprot.readDouble();
            setLongitudeIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 5: // ALTITUDE
          if (field.type == TType.DOUBLE) {
            this.altitude = iprot.readDouble();
            setAltitudeIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 6: // CAMERA_MAKE
          if (field.type == TType.STRING) {
            this.cameraMake = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 7: // CAMERA_MODEL
          if (field.type == TType.STRING) {
            this.cameraModel = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 8: // CLIENT_WILL_INDEX
          if (field.type == TType.BOOL) {
            this.clientWillIndex = iprot.readBool();
            setClientWillIndexIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 9: // RECO_TYPE
          if (field.type == TType.STRING) {
            this.recoType = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 10: // FILE_NAME
          if (field.type == TType.STRING) {
            this.fileName = iprot.readString();
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 11: // ATTACHMENT
          if (field.type == TType.BOOL) {
            this.attachment = iprot.readBool();
            setAttachmentIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 12: // APPLICATION_DATA
          if (field.type == TType.STRUCT) {
            this.applicationData = new LazyMap();
            this.applicationData.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.sourceURL != null) {
      if (isSetSourceURL()) {
        oprot.writeFieldBegin(SOURCE_URL_FIELD_DESC);
        oprot.writeString(this.sourceURL);
        oprot.writeFieldEnd();
      }
    }
    if (isSetTimestamp()) {
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(this.timestamp);
      oprot.writeFieldEnd();
    }
    if (isSetLatitude()) {
      oprot.writeFieldBegin(LATITUDE_FIELD_DESC);
      oprot.writeDouble(this.latitude);
      oprot.writeFieldEnd();
    }
    if (isSetLongitude()) {
      oprot.writeFieldBegin(LONGITUDE_FIELD_DESC);
      oprot.writeDouble(this.longitude);
      oprot.writeFieldEnd();
    }
    if (isSetAltitude()) {
      oprot.writeFieldBegin(ALTITUDE_FIELD_DESC);
      oprot.writeDouble(this.altitude);
      oprot.writeFieldEnd();
    }
    if (this.cameraMake != null) {
      if (isSetCameraMake()) {
        oprot.writeFieldBegin(CAMERA_MAKE_FIELD_DESC);
        oprot.writeString(this.cameraMake);
        oprot.writeFieldEnd();
      }
    }
    if (this.cameraModel != null) {
      if (isSetCameraModel()) {
        oprot.writeFieldBegin(CAMERA_MODEL_FIELD_DESC);
        oprot.writeString(this.cameraModel);
        oprot.writeFieldEnd();
      }
    }
    if (isSetClientWillIndex()) {
      oprot.writeFieldBegin(CLIENT_WILL_INDEX_FIELD_DESC);
      oprot.writeBool(this.clientWillIndex);
      oprot.writeFieldEnd();
    }
    if (this.recoType != null) {
      if (isSetRecoType()) {
        oprot.writeFieldBegin(RECO_TYPE_FIELD_DESC);
        oprot.writeString(this.recoType);
        oprot.writeFieldEnd();
      }
    }
    if (this.fileName != null) {
      if (isSetFileName()) {
        oprot.writeFieldBegin(FILE_NAME_FIELD_DESC);
        oprot.writeString(this.fileName);
        oprot.writeFieldEnd();
      }
    }
    if (isSetAttachment()) {
      oprot.writeFieldBegin(ATTACHMENT_FIELD_DESC);
      oprot.writeBool(this.attachment);
      oprot.writeFieldEnd();
    }
    if (this.applicationData != null) {
      if (isSetApplicationData()) {
        oprot.writeFieldBegin(APPLICATION_DATA_FIELD_DESC);
        this.applicationData.write(oprot);
        oprot.writeFieldEnd();
      }
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ResourceAttributes(");
    boolean first = true;

    if (isSetSourceURL()) {
      sb.append("sourceURL:");
      if (this.sourceURL == null) {
        sb.append("null");
      } else {
        sb.append(this.sourceURL);
      }
      first = false;
    }
    if (isSetTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("timestamp:");
      sb.append(this.timestamp);
      first = false;
    }
    if (isSetLatitude()) {
      if (!first) sb.append(", ");
      sb.append("latitude:");
      sb.append(this.latitude);
      first = false;
    }
    if (isSetLongitude()) {
      if (!first) sb.append(", ");
      sb.append("longitude:");
      sb.append(this.longitude);
      first = false;
    }
    if (isSetAltitude()) {
      if (!first) sb.append(", ");
      sb.append("altitude:");
      sb.append(this.altitude);
      first = false;
    }
    if (isSetCameraMake()) {
      if (!first) sb.append(", ");
      sb.append("cameraMake:");
      if (this.cameraMake == null) {
        sb.append("null");
      } else {
        sb.append(this.cameraMake);
      }
      first = false;
    }
    if (isSetCameraModel()) {
      if (!first) sb.append(", ");
      sb.append("cameraModel:");
      if (this.cameraModel == null) {
        sb.append("null");
      } else {
        sb.append(this.cameraModel);
      }
      first = false;
    }
    if (isSetClientWillIndex()) {
      if (!first) sb.append(", ");
      sb.append("clientWillIndex:");
      sb.append(this.clientWillIndex);
      first = false;
    }
    if (isSetRecoType()) {
      if (!first) sb.append(", ");
      sb.append("recoType:");
      if (this.recoType == null) {
        sb.append("null");
      } else {
        sb.append(this.recoType);
      }
      first = false;
    }
    if (isSetFileName()) {
      if (!first) sb.append(", ");
      sb.append("fileName:");
      if (this.fileName == null) {
        sb.append("null");
      } else {
        sb.append(this.fileName);
      }
      first = false;
    }
    if (isSetAttachment()) {
      if (!first) sb.append(", ");
      sb.append("attachment:");
      sb.append(this.attachment);
      first = false;
    }
    if (isSetApplicationData()) {
      if (!first) sb.append(", ");
      sb.append("applicationData:");
      if (this.applicationData == null) {
        sb.append("null");
      } else {
        sb.append(this.applicationData);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

