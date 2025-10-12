
import TaskItem from './TaskItem';

function TaskList({ tasks, onDelete, onEdit, onStatusChange }) {
  if (tasks.length === 0) {
    return <p>No tasks found. Create your first task!</p>;
  }

  return (
    <div style={{ marginTop: '20px' }}>
      <h2>Tasks ({tasks.length})</h2>
      {tasks.map(task => (
        <TaskItem
          key={task.id}
          task={task}
          onDelete={onDelete}
          onEdit={onEdit}
          onStatusChange={onStatusChange}
        />
      ))}
    </div>
  );
}

export default TaskList;