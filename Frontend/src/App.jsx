
import { useState, useEffect } from 'react';
import taskService from './services/TaskService';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';
import { TaskStatus, TaskPriority } from './utils/Constants';

function App() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingTask, setEditingTask] = useState(null);
  const [showForm, setShowForm] = useState(false);

  // Load tasks on mount
  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
    try {
      setLoading(true);
      const data = await taskService.getAllTasks();
      setTasks(data);
      setError(null);
    } catch (err) {
      setError('Failed to load tasks');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleCreateTask = async (taskData) => {
    try {
      await taskService.createTask(taskData);
      loadTasks();
      setShowForm(false);
    } catch (err) {
      setError('Failed to create task');
      console.error(err);
    }
  };

  const handleUpdateTask = async (id, taskData) => {
    try {
      await taskService.updateTask(id, taskData);
      loadTasks();
      setEditingTask(null);
      setShowForm(false);
    } catch (err) {
      setError('Failed to update task');
      console.error(err);
    }
  };

  const handleDeleteTask = async (id) => {
    if (window.confirm('Are you sure you want to delete this task?')) {
      try {
        await taskService.deleteTask(id);
        loadTasks();
      } catch (err) {
        setError('Failed to delete task');
        console.error(err);
      }
    }
  };

  const handleStatusChange = async (id, newStatus) => {
    try {
      if (newStatus === TaskStatus.DONE) {
        await taskService.markAsCompleted(id);
      } else if (newStatus === TaskStatus.IN_PROGRESS) {
        await taskService.markAsInProgress(id);
      } else {
        await taskService.markAsTodo(id);
      }
      loadTasks();
    } catch (err) {
      setError('Failed to update task status');
      console.error(err);
    }
  };

  const handleEdit = (task) => {
    setEditingTask(task);
    setShowForm(true);
  };

  const handleCancelEdit = () => {
    setEditingTask(null);
    setShowForm(false);
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div style={{ padding: '20px', maxWidth: '1200px', margin: '0 auto' }}>
      <h1>Task Manager</h1>
      
      {error && (
        <div style={{ color: 'red', marginBottom: '10px' }}>
          {error}
        </div>
      )}

      <button onClick={() => setShowForm(!showForm)}>
        {showForm ? 'Cancel' : 'Add New Task'}
      </button>

      {showForm && (
        <TaskForm
          task={editingTask}
          onSubmit={editingTask ? 
            (data) => handleUpdateTask(editingTask.id, data) : 
            handleCreateTask
          }
          onCancel={handleCancelEdit}
        />
      )}

      <TaskList
        tasks={tasks}
        onDelete={handleDeleteTask}
        onEdit={handleEdit}
        onStatusChange={handleStatusChange}
      />
    </div>
  );
}

export default App;