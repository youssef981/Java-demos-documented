In java we have Thread pool and Queue waiting of task, and for example let's say we have three thread in a pool those threads will work on three tasks and the forth task will stay waiting in the waiting queue.

plus of that each thread have its own dequeue list where they can divide a task and divide into sub-tasks and they will start working on a sub-task and other sub-tasks they will stay waiting in the waiting queue technically named (`WorkStealingQueue`) why this name because any of the other available threads in the thread pool like finished working with their own task they will start stealing work on the sub-tasks from other busy threads.

In coding this actually possible through two ways the `RecursiveAction` and the `RecursiveTask` the first one is used for cases where we have a value to return and the second one is used for non-return values. Exampale:

