package server.databaseManagement;

public class ManagerHolderImpl2 implements ManagerHolder {
    private static DatabaseOps databaseOps = new DatabaseOps();
    private static NotificationManager notificationManager = new NotificationManager();

    @Override
    public DatabaseOps getDataBase() {
        return databaseOps;
    }

    @Override
    public NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
