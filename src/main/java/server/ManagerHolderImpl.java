package server;

public class ManagerHolderImpl implements ManagerHolder {
    private DatabaseOps databaseOps = new DatabaseOps();
    private NotificationManager notificationManager = new NotificationManager();

    @Override
    public DatabaseOps getDataBase() {
        return databaseOps;
    }

    @Override
    public NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
