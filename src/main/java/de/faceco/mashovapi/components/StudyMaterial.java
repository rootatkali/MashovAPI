package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import de.faceco.mashovapi.API;

import java.util.Objects;

public final class StudyMaterial {
  private String fileId;
  private String fileName;
  private String ownerGroupType;
  private String ownerGroup;
  private String uploaderId;
  private String uploadTime;
  
  StudyMaterial() {
  
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
  
  public String getDownloadLink() {
    return String.format("%s/students/%s/files/%s/%s",
        RequestController.BASE_URL,
        API.getInstance().getStudentId(),
        fileId,
        fileName);
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
    StudyMaterial that = (StudyMaterial) o;
    return Objects.equals(fileId, that.fileId) &&
        Objects.equals(fileName, that.fileName) &&
        Objects.equals(ownerGroupType, that.ownerGroupType) &&
        Objects.equals(ownerGroup, that.ownerGroup) &&
        Objects.equals(uploaderId, that.uploaderId) &&
        Objects.equals(uploadTime, that.uploadTime);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(fileId, fileName, ownerGroupType, ownerGroup, uploaderId, uploadTime);
  }
}
