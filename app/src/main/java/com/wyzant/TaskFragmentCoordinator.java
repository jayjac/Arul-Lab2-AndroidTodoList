package com.wyzant;

import androidx.annotation.NonNull;

/**
 * Any activity that must coordinate tasks between two fragments needs to implement this interface.
 * In our case, only the MainActivity needs to implement this.
 */
public interface TaskFragmentCoordinator {
    /**
     * Called from a fragment when the user adds a task
     * @param name name of the task
     */
    void taskWasAdded(@NonNull String name);

    /**
     * Called from a fragment when the user marks the task as complete / active
     * @param item the task to toggle
     */
    void taskCompletenessWasToggled(Task item);

    /**
     * When a task is removed from a fragment
     * @param item the task to remove
     */
    void taskWasRemoved(Task item); //IMPLEMENT
}
