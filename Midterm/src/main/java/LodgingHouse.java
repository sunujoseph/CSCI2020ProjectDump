public class LodgingHouse {
  private int objectId;
  private String streetName;
  private String propertyClass;
  private double shapeArea;
  private double shapeLength;

  public LodgingHouse(int objectId,
                      String streetName,
                      String propertyClass,
                      double shapeArea,
                      double shapeLength) {
      this.objectId = objectId;
      this.streetName = streetName;
      this.propertyClass = propertyClass;
      this.shapeArea = shapeArea;
      this.shapeLength = shapeLength;

  }

  public int getObjectId() { return objectId; }
  public void setObjectId(int objId) { objectId = objId; }

  public String getStreetName() { return streetName; }
  public void setStreetName(String street) { streetName = street; }

  public String getPropertyClass() { return propertyClass; }
  public void setPropertyClass(String propClass) { propertyClass = propClass; }

  public double getShapeArea() { return shapeArea; }
  public void setShapeArea(double area) { shapeArea = area; }

  public double getShapeLength() { return shapeLength; }
  public void setShapeLength(double length) { shapeLength = length; }

  public String toString() {
    return new String(objectId + ", " +
                      streetName + ", " +
                      propertyClass + ", " +
                      shapeArea + ", " +
                      shapeLength);
  }
}
