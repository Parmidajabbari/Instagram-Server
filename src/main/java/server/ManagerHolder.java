package server;

public interface ManagerHolder {

    DatabaseOps getDataBase();
    NotificationManager getNotificationManager();

}
