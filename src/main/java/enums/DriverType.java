
package enums;

public enum DriverType {
    CHROME, FIREFOX, INTERNETEXPLORER;

    public static DriverType getDriverType(String driverType) {
        return valueOf(driverType.toUpperCase());
    }
}