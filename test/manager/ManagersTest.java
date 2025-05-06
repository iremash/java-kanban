package manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    Managers managers = new Managers();

    @Test
    void getDefaultReturnsReadyForWorkManager() {
        TaskManager taskManager = managers.getDefault();
        assertNotNull(taskManager, "Менеджер не возращается");
    }

    @Test
    void getDefaultHistoryReturnsReadyForWorkHistoryManager() {
        HistoryManager historyManager = managers.getDefaultHistory();
        assertNotNull(historyManager, "Менеджер не возращается");
    }
}