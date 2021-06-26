package server.databaseManagement;

public interface ManagerHolder {

    DatabaseOps getDataBase();
    NotificationManager getNotificationManager();

}
