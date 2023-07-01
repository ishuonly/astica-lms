
package server;

public class User {
    public String Username, SystemID, Hash_Key, MotherboardSN, CPU_ID, MACAddress;
    public int Subscription;

    public User(String Username, String SystemID, String Hash_Key, String MotherboardSN, String CPU_ID, String MACAddress, int Subscription ){
        this.Username = Username;
        this.SystemID = SystemID;
        this.Hash_Key = Hash_Key;
        this.MotherboardSN = MotherboardSN;
        this.CPU_ID = CPU_ID;
        this.MACAddress = MACAddress;
        this.Subscription = Subscription;
    }
    public int getSubscription(){
        return Subscription;
    }
    public String getUsername() {
        return Username;
    }
    public String getSystemID() {
        return SystemID;
    }
    public String getHash_Key() {
        return Hash_Key;
    }
    public String getMotherboardSN() {
        return MotherboardSN;
    }
    public String getCPU_ID() {
        return CPU_ID;
    }
    public String getMACAddress() {
        return MACAddress;
    }
}
