package manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static manager.Managers.*;

class ManagersTest {
    @Test
    void getDefaultReturnsReadyForWorkManager() {
        TaskManager taskManager = getDefault();
        assertNotNull(taskManager, "Менеджер не возращается");
    }

    @Test
    void getDefaultHistoryReturnsReadyForWorkHistoryManager() {
        HistoryManager historyManager = getDefaultHistory();
        assertNotNull(historyManager, "Менеджер не возращается");
    }

}