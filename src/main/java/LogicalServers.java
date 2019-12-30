import java.util.ArrayList;

public class LogicalServers {

    public String Name;
    public String EntryCountry;
    public String ExitCountry;
    public String Domain;
    public int Tier;
    public int Features;
    public String Region;
    public String City;
    public String ID;
    public int Status;
    public int Load;
    public double Score;

    public ArrayList<Servers> Servers;

    Location Location;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEntryCountry() {
        return EntryCountry;
    }

    public void setEntryCountry(String entryCountry) {
        EntryCountry = entryCountry;
    }

    public String getExitCountry() {
        return ExitCountry;
    }

    public void setExitCountry(String exitCountry) {
        ExitCountry = exitCountry;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public int getTier() {
        return Tier;
    }

    public void setTier(int tier) {
        Tier = tier;
    }

    public int getFeatures() {
        return Features;
    }

    public void setFeatures(int features) {
        Features = features;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getLoad() {
        return Load;
    }

    public void setLoad(int load) {
        Load = load;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public ArrayList<Servers> getServers() {
        return Servers;
    }

    public void setServers(ArrayList<Servers> servers) {
        Servers = servers;
    }

    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location location) {
        Location = location;
    }

    public LogicalServers(String name, String entryCountry, String exitCountry, String domain, int tier, int features, String region, String city, String ID, int status, int load, double score, ArrayList<Servers> servers, Location location) {
        Name = name;
        EntryCountry = entryCountry;
        ExitCountry = exitCountry;
        Domain = domain;
        Tier = tier;
        Features = features;
        Region = region;
        City = city;
        this.ID = ID;
        Status = status;
        Load = load;
        Score = score;
        Servers = servers;
        Location = location;
    }
}
