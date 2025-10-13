import { useState, useEffect } from 'react';
import KanbanBoard from './components/KanbanBoard';
import TaskModal from './components/TaskModal';
import taskService from './services/taskService';
import Dashboard from './components/Dashboard';

function App() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [darkMode, setDarkMode] = useState(false);
  const [currentView, setCurrentView] = useState('dashboard');
  const [showTaskModal, setShowTaskModal] = useState(false);
  const [editingTask, setEditingTask] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterPriority, setFilterPriority] = useState('ALL');

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
      setShowTaskModal(false);
    } catch (err) {
      setError('Failed to create task');
    }
  };

  const handleUpdateTask = async (id, taskData) => {
    try {
      await taskService.updateTask(id, taskData);
      loadTasks();
      setEditingTask(null);
      setShowTaskModal(false);
    } catch (err) {
      setError('Failed to update task');
    }
  };

  const handleDeleteTask = async (id) => {
    if (window.confirm('Are you sure you want to delete this task?')) {
      try {
        await taskService.deleteTask(id);
        loadTasks();
      } catch (err) {
        setError('Failed to delete task');
      }
    }
  };

  const handleStatusChange = async (id, newStatus) => {
    try {
      if (newStatus === 'DONE') {
        await taskService.markAsCompleted(id);
      } else if (newStatus === 'IN_PROGRESS') {
        await taskService.markAsInProgress(id);
      } else {
        await taskService.markAsTodo(id);
      }
      loadTasks();
    } catch (err) {
      setError('Failed to update task status');
    }
  };

  const filteredTasks = tasks.filter(task => {
    const matchesSearch = task.title.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesPriority = filterPriority === 'ALL' || task.priority === filterPriority;
    return matchesSearch && matchesPriority;
  });

  if (loading) {
    return (
      <div style={{
        minHeight: '100vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: darkMode ? '#111827' : '#f9fafb'
      }}>
        <div style={{ fontSize: '20px' }}>Loading...</div>
      </div>
    );
  }

  return (
    <div style={{
      minHeight: '100vh',
      backgroundColor: darkMode ? '#111827' : '#f9fafb',
      color: darkMode ? 'white' : '#111827'
    }}>
      {/* Header */}
      <header style={{
        backgroundColor: darkMode ? '#1f2937' : 'white',
        borderBottom: `1px solid ${darkMode ? '#374151' : '#e5e7eb'}`,
        position: 'sticky',
        top: 0,
        zIndex: 10
      }}>
        <div style={{ maxWidth: '1280px', margin: '0 auto', padding: '16px' }}>
          <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '16px' }}>
            <h1 style={{ fontSize: '24px', fontWeight: 'bold' }}>Task Manager</h1>
            
            <div style={{ display: 'flex', gap: '12px', alignItems: 'center' }}>
              <button
                onClick={() => setCurrentView('dashboard')}
                style={{
                  padding: '8px 16px',
                  borderRadius: '8px',
                  border: 'none',
                  cursor: 'pointer',
                  backgroundColor: currentView === 'dashboard' ? '#2563eb' : (darkMode ? '#374151' : '#e5e7eb'),
                  color: currentView === 'dashboard' ? 'white' : (darkMode ? 'white' : 'black')
                }}
              >
                📊 Dashboard
              </button>
              <button
                onClick={() => setCurrentView('kanban')}
                style={{
                  padding: '8px 16px',
                  borderRadius: '8px',
                  border: 'none',
                  cursor: 'pointer',
                  backgroundColor: currentView === 'kanban' ? '#2563eb' : (darkMode ? '#374151' : '#e5e7eb'),
                  color: currentView === 'kanban' ? 'white' : (darkMode ? 'white' : 'black')
                }}
              >
                📋 Kanban
              </button>
              <button
                onClick={() => setDarkMode(!darkMode)}
                style={{
                  padding: '8px 16px',
                  borderRadius: '8px',
                  border: 'none',
                  cursor: 'pointer',
                  backgroundColor: darkMode ? '#374151' : '#e5e7eb',
                  color: darkMode ? 'white' : 'black'
                }}
              >
                {darkMode ? '☀️' : '🌙'}
              </button>
              <button
                onClick={() => {
                  setEditingTask(null);
                  setShowTaskModal(true);
                }}
                style={{
                  padding: '8px 16px',
                  borderRadius: '8px',
                  border: 'none',
                  cursor: 'pointer',
                  backgroundColor: '#2563eb',
                  color: 'white',
                  fontWeight: '600'
                }}
              >
                ➕ New Task
              </button>
            </div>
          </div>

          <div style={{ display: 'flex', gap: '12px' }}>
            <input
              type="text"
              placeholder="🔍 Search tasks..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              style={{
                flex: 1,
                padding: '8px 12px',
                borderRadius: '8px',
                border: `1px solid ${darkMode ? '#374151' : '#d1d5db'}`,
                backgroundColor: darkMode ? '#111827' : 'white',
                color: darkMode ? 'white' : 'black'
              }}
            />
            <select
              value={filterPriority}
              onChange={(e) => setFilterPriority(e.target.value)}
              style={{
                padding: '8px 12px',
                borderRadius: '8px',
                border: `1px solid ${darkMode ? '#374151' : '#d1d5db'}`,
                backgroundColor: darkMode ? '#111827' : 'white',
                color: darkMode ? 'white' : 'black'
              }}
            >
              <option value="ALL">All Priorities</option>
              <option value="LOW">Low</option>
              <option value="MEDIUM">Medium</option>
              <option value="HIGH">High</option>
              <option value="URGENT">Urgent</option>
            </select>
          </div>
        </div>
      </header>

      {error && (
        <div style={{
          maxWidth: '1280px',
          margin: '16px auto',
          padding: '0 16px'
        }}>
          <div style={{
            backgroundColor: '#fee2e2',
            border: '1px solid #fca5a5',
            color: '#991b1b',
            padding: '12px 16px',
            borderRadius: '8px'
          }}>
            {error}
          </div>
        </div>
      )}

      <main style={{ maxWidth: '1280px', margin: '0 auto', padding: '24px 16px' }}>
        {currentView === 'dashboard' ? (
          <Dashboard tasks={filteredTasks} darkMode={darkMode} />
        ) : (
          <KanbanBoard
            tasks={filteredTasks}
            onStatusChange={handleStatusChange}
            onEdit={(task) => {
              setEditingTask(task);
              setShowTaskModal(true);
            }}
            onDelete={handleDeleteTask}
            darkMode={darkMode}
          />
        )}
      </main>

      {showTaskModal && (
        <TaskModal
          task={editingTask}
          onSubmit={editingTask ? 
            (data) => handleUpdateTask(editingTask.id, data) : 
            handleCreateTask
          }
          onClose={() => {
            setShowTaskModal(false);
            setEditingTask(null);
          }}
          darkMode={darkMode}
        />
      )}
    </div>
  );
}

export default App;