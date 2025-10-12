
import { useState, useEffect } from 'react';
import { TaskStatus, TaskPriority } from '../utils/Constants';

function TaskForm({ task, onSubmit, onCancel }) {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    status: TaskStatus.TODO,
    priority: TaskPriority.MEDIUM
  });

  useEffect(() => {
    if (task) {
      setFormData({
        title: task.title,
        description: task.description || '',
        status: task.status,
        priority: task.priority
      });
    }
  }, [task]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!formData.title.trim()) {
      alert('Title is required');
      return;
    }
    onSubmit(formData);
    setFormData({
      title: '',
      description: '',
      status: TaskStatus.TODO,
      priority: TaskPriority.MEDIUM
    });
  };

  return (
    <form onSubmit={handleSubmit} style={{
      border: '1px solid #ccc',
      padding: '20px',
      marginTop: '20px',
      marginBottom: '20px',
      borderRadius: '5px'
    }}>
      <h3>{task ? 'Edit Task' : 'Create New Task'}</h3>
      
      <div style={{ marginBottom: '10px' }}>
        <label>
          Title *
          <input
            type="text"
            name="title"
            value={formData.title}
            onChange={handleChange}
            required
            style={{ width: '100%', padding: '5px', marginTop: '5px' }}
          />
        </label>
      </div>

      <div style={{ marginBottom: '10px' }}>
        <label>
          Description
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            rows="3"
            style={{ width: '100%', padding: '5px', marginTop: '5px' }}
          />
        </label>
      </div>

      <div style={{ marginBottom: '10px' }}>
        <label>
          Status
          <select
            name="status"
            value={formData.status}
            onChange={handleChange}
            style={{ width: '100%', padding: '5px', marginTop: '5px' }}
          >
            <option value={TaskStatus.TODO}>To Do</option>
            <option value={TaskStatus.IN_PROGRESS}>In Progress</option>
            <option value={TaskStatus.DONE}>Done</option>
          </select>
        </label>
      </div>

      <div style={{ marginBottom: '10px' }}>
        <label>
          Priority
          <select
            name="priority"
            value={formData.priority}
            onChange={handleChange}
            style={{ width: '100%', padding: '5px', marginTop: '5px' }}
          >
            <option value={TaskPriority.LOW}>Low</option>
            <option value={TaskPriority.MEDIUM}>Medium</option>
            <option value={TaskPriority.HIGH}>High</option>
            <option value={TaskPriority.URGENT}>Urgent</option>
          </select>
        </label>
      </div>

      <div style={{ display: 'flex', gap: '10px' }}>
        <button type="submit">{task ? 'Update' : 'Create'} Task</button>
        <button type="button" onClick={onCancel}>Cancel</button>
      </div>
    </form>
  );
}

export default TaskForm;