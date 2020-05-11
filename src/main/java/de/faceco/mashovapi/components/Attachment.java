package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;

public final class Attachment {
  private String fileId;
  private String fileName;
  private String ownerGroupType;
  private String ownerGroup;
  private String uploaderId;
  private String uploadTime;
  
  Attachment() {
  
  }
  
  public String getFileId() {
    return fileId;
  }
  
  public String getFileName() {
    return fileName;
  }
  
  public String getOwnerGroupType() {
    return ownerGroupType;
  }
  
  public String getOwnerGroup() {
    return ownerGroup;
  }
  
  public String getUploaderId() {
    return uploaderId;
  }
  
  public String getUploadTime() {
    return uploadTime;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("fileId", fileId)
        .add("fileName", fileName)
        .add("ownerGroupType", ownerGroupType)
        .add("ownerGroup", ownerGroup)
        .add("uploaderId", uploaderId)
        .add("uploadTime", uploadTime)
        .toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Attachment attachment = (Attachment) o;
    
    if (!fileId.equals(attachment.fileId)) return false;
    if (!fileName.equals(attachment.fileName)) return false;
    if (!ownerGroupType.equals(attachment.ownerGroupType)) return false;
    if (!ownerGroup.equals(attachment.ownerGroup)) return false;
    if (!uploaderId.equals(attachment.uploaderId)) return false;
    return uploadTime.equals(attachment.uploadTime);
  }
  
  @Override
  public int hashCode() {
    int result = fileId.hashCode();
    result = 31 * result + fileName.hashCode();
    result = 31 * result + ownerGroupType.hashCode();
    result = 31 * result + ownerGroup.hashCode();
    result = 31 * result + uploaderId.hashCode();
    result = 31 * result + uploadTime.hashCode();
    return result;
  }
}
