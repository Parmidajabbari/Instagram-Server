package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class SearchTask extends server.requests.Task {

    private String searchedName;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            int res = databaseOps.usernameToID(searchedName);
            if( res == -1 )
                result = "{'task' : 'search', 'error' : true, 'Result' : 'Nothing has found!'}";
            else
                result = "{'task' : 'search', 'error' : false, 'Result' : " + res +" }";

        }
        catch (Exception e){
            result = "{'task' : 'search', 'error' : true, 'Result' : 'something went wrong!'}";
        }

        return result;
    }

    // get the requested name from database and respond

}
