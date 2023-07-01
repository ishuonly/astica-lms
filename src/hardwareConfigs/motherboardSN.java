package hardwareConfigs;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;

public class motherboardSN {
    
    public static String getmotherboardSN(){
        SystemInfo systemInfo = new SystemInfo();
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        String motherboardSerialNumber = computerSystem.getSerialNumber();

        return motherboardSerialNumber;
    }
    public static void main(String[] args) {
        String motherboardSerialNumber = getmotherboardSN();
        System.out.println("motherB_SN: "+motherboardSerialNumber);
    }
}
