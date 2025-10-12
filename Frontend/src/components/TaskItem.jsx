
import { TaskStatus, StatusLabels, PriorityLabels } from '../utils/Constants';

function TaskItem({ task, onDelete, onEdit, onStatusChange }) {
  return (
    <div style={{
      border: '1px solid #ccc',
      padding: '15px',
      marginBottom: '10px',
      borderRadius: '5px'
    }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'start' }}>
        <div style={{ flex: 1 }}>
          <h3>{task.title}</h3>
          {task.description && <p>{task.description}</p>}
          
          <div style={{ marginTop: '10px', fontSize: '14px', color: '#666' }}>
            <span><strong>Status:</strong> {StatusLabels[task.status]} | </span>
            <span><strong>Priority:</strong> {PriorityLabels[task.priority]}</span>
          </div>
          
          <div style={{ marginTop: '5px', fontSize: '12px', color: '#999' }}>
            Created: {new Date(task.createdAt).toLocaleString()}
          </div>
        </div>

        <div style={{ display: 'flex', flexDirection: 'column', gap: '5px' }}>
          <button onClick={() => onEdit(task)}>Edit</button>
          <button onClick={() => onDelete(task.id)}>Delete</button>
          
          <select 
            value={task.status} 
            onChange={(e) => onStatusChange(task.id, e.target.value)}
          >
            <option value={TaskStatus.TODO}>To Do</option>
            <option value={TaskStatus.IN_PROGRESS}>In Progress</option>
            <option value={TaskStatus.DONE}>Done</option>
          </select>
        </div>
      </div>
    </div>
  );
}

export default TaskItem;