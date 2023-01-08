import java.util.*;

public class Main {

    static Scanner scanner;
    static TaskManager manager;

    public static void main(String[] args) {

        manager = new TaskManager();
        scanner = new Scanner(System.in);
        printMainMenu();
        String userInput = scanner.nextLine();

        while (!userInput.equals("0")) {

            switch (userInput) {
                case "1":
                    printTasksList();
                    break;
                case "2":
                    printCreateMenu();
                    break;
                case "3":
                    printEditMenu();
                    break;
                case "4":
                    deleteTasksMenu();
                    break;
                default:
                    System.out.println("Некорректное значение меню.");
            }

            printMainMenu();
            userInput = scanner.nextLine();
        }
        System.out.println("Программа завершена.");
    }

    private static void printMainMenu() {
        System.out.println("1. Получить список всех задач.");
        System.out.println("2. Создать новую задачу.");
        System.out.println("3. Редактировать задачу.");
        System.out.println("4. Удалить задачи.");
        System.out.println("0. Выйти из приложения.");
    }

    private static void deleteTasksMenu() {
        System.out.println("1. Удалить отдельную задачу.");
        System.out.println("2. Удалить все задачи.");
        System.out.println("3. В главное меню.");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                deleteTask();
                break;
            case "2":
                clearTasks();
                break;
            case "3":
            default:
                System.out.println("Некорректное значение меню.");
        }
    }

    private static void deleteTask() {
        printTasksList();
        System.out.println("Введите номер задачи.");
        String userInput = scanner.nextLine();

        if (manager.getTaskById(Integer.parseInt(userInput)) != null) {
            manager.deleteTaskById(Integer.parseInt(userInput));
            System.out.println("Задача " + "#" + userInput + " удалена.");
        } else {
            System.out.println("Некорректное значение номера задачи.");
        }

    }

    private static void clearTasks() {
        System.out.println("Вы действительно хотите удалить все задачи?");
        System.out.println("1. Да.");
        System.out.println("2. Нет.");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                manager.clearTasks();
                System.out.println("Все задачи удалены.");
                break;
            case "2":
            default:
                System.out.println("Некорректное значение меню.");
        }
    }

    private static void printEditMenu() {
        printTasksList();
        System.out.println("Введите номер задачи.");
        String userInput = scanner.nextLine();

        Object task = manager.getTaskById(Integer.parseInt(userInput));

        if (task != null) {
            if (task.getClass().getSimpleName().equals("Task")) {
                editTask((Task) task);
            }

            if (task.getClass().getSimpleName().equals("Epic")) {
                editEpic((Epic) task);
            }
        } else {
            System.out.println("Некорректный номер задачи");
        }
    }

    private static void editTask(Task task) {
        System.out.println(task);
        System.out.println("Введите название задачи. (Нажмите ENTER чтобы оставить название без изменения)");
        String userInput = scanner.nextLine();
        if (!userInput.equals("")) {
            task.setTitle(userInput);
        }
        System.out.println("Введите описание задачи. (Нажмите ENTER чтобы оставить описание без изменения)");
        userInput = scanner.nextLine();
        if (!userInput.equals("")) {
            task.setDescription(userInput);
        }
        System.out.println("Введите статус задачи. (Нажмите ENTER чтобы оставить статус без изменения)");
        System.out.println("1. Новая.");
        System.out.println("2. В процессе выполнения.");
        System.out.println("3. Завершена.");
        userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                break;
            case "2":
                task.setStatusInProgress();
                break;
            case "3":
                task.setStatusDone();
                break;
            case "":
            default:
                System.out.println("Некорректное значение статуса.");
        }
        manager.updateTask(task);
        System.out.println("Задача " + "#" + task.getId() + " обновлена.");
    }

    private static void editEpic(Epic epic) {
        System.out.println(epic);
        System.out.println("Введите название эпика. (Нажмите ENTER чтобы оставить название без изменения)");
        String userInput = scanner.nextLine();
        if (!userInput.equals("")) {
            epic.setTitle(userInput);
        }
        System.out.println("Введите описание эпика. (Нажмите ENTER чтобы оставить описание без изменения)");
        userInput = scanner.nextLine();
        if (!userInput.equals("")) {
            epic.setDescription(userInput);
        }

        Collection<Subtask> subtasks = manager.getEpicSubtasks(epic);
        for (Subtask subtask : subtasks) {
            System.out.println(subtask);
            System.out.println("Введите название подзадачи. (Нажмите ENTER чтобы оставить название без изменения)");
            userInput = scanner.nextLine();
            if (!userInput.equals("")) {
                subtask.setTitle(userInput);
            }
            System.out.println("Введите описание подзадачи. (Нажмите ENTER чтобы оставить описание без изменения)");
            userInput = scanner.nextLine();
            if (!userInput.equals("")) {
                subtask.setDescription(userInput);
            }
            System.out.println("Введите статус подзадачи. (Нажмите ENTER чтобы оставить статус без изменения)");
            System.out.println("1. Новая.");
            System.out.println("2. В процессе выполнения.");
            System.out.println("3. Завершена.");
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    subtask.setStatusNew();
                    break;
                case "2":
                    subtask.setStatusInProgress();
                    break;
                case "3":
                    subtask.setStatusDone();
                    break;
                case "":
                default:
                    System.out.println("Некорректное значение статуса.");
            }
        }

        epic.updateSubtasks(subtasks);
        manager.updateTask(epic);
        System.out.println("Эпик " + "#" + epic.getId() + " обновлен.");
    }

    private static void printCreateMenu() {
        System.out.println("1. Создать задачу.");
        System.out.println("2. Создать эпик.");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                createNewTask();
                break;
            case "2":
                createNewEpic();
                break;
            default:
                System.out.println("Некорректное значение меню.");
        }
    }

    private static void createNewTask() {
        System.out.println("Введите название задачи.");
        String userInput = scanner.nextLine();
        String title = userInput;
        System.out.println("Введите описание задачи.");
        userInput = scanner.nextLine();
        String description = userInput;

        Task task = new Task(title, description);
        task.setStatusNew();
        manager.addTask(task);

        System.out.println("Задача успешно создана.");
    }

    private static void createNewEpic() {
        System.out.println("Введите название эпика.");
        String userInput = scanner.nextLine();
        String epicTitle = userInput;
        System.out.println("Введите описание эпика.");
        userInput = scanner.nextLine();
        String epicDescription = userInput;

        ArrayList<String> subtaskTitles = new ArrayList<>();
        ArrayList<String> subtaskDescriptions = new ArrayList<>();

        do {
            System.out.println("Введите название подзадачи. Для завершения введите 0.");
            userInput = scanner.nextLine();
            if (userInput.equals("0")) {
                break;
            }
            subtaskTitles.add(userInput);
            System.out.println("Введите описание подзадачи. Для завершения введите 0.");
            userInput = scanner.nextLine();
            if (userInput.equals("0")) {
                break;
            }
            subtaskDescriptions.add(userInput);
        } while (true);

        Epic epic = new Epic(epicTitle, epicDescription);

        for (int i = 0; i < subtaskTitles.size(); i++) {
            Subtask subtask;
            if (i + 1 <= subtaskDescriptions.size()) {
                subtask = new Subtask(epic.getId(), subtaskTitles.get(i), subtaskDescriptions.get(i));
            } else {
                subtask = new Subtask(epic.getId(), subtaskTitles.get(i));
            }
            epic.addSubtask(subtask);
        }
        manager.addTask(epic);

        System.out.println("Эпик успешно создан.");
    }

    private static void printTasksList() {
        Collection<TaskInterface> tasks = manager.getTasks();

        System.out.println("=======================");
        for (Object task : tasks) {
            System.out.println(task);
            System.out.println("=======================");
        }
    }

}