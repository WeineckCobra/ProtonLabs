import java.util.ArrayList;

public class Proton {

    public String Code;
    public ArrayList<LogicalServers> LogicalServers;

    public ArrayList<LogicalServers> getLogicalServers() { return LogicalServers; }

    public void setLogicalServers(ArrayList<LogicalServers> logicalServers) {
        LogicalServers = logicalServers;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = Code;
    }

}
