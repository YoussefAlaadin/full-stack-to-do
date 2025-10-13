import { Edit2, Trash2, AlertCircle, Clock, CheckCircle2 } from 'lucide-react';

function KanbanBoard({ tasks, onStatusChange, onEdit, onDelete, darkMode }) {
  const columns = [
    { id: 'TODO', title: 'To Do', icon: Clock, color: 'blue' },
    { id: 'IN_PROGRESS', title: 'In Progress', icon: AlertCircle, color: 'yellow' },
    { id: 'DONE', title: 'Done', icon: CheckCircle2, color: 'green' }
  ];

  const getTasksByStatus = (status) => {
    return tasks.filter(task => task.status === status);
  };

  const getPriorityColor = (priority) => {
    const colors = {
      LOW: darkMode ? 'bg-gray-700 text-gray-300' : 'bg-gray-200 text-gray-700',
      MEDIUM: darkMode ? 'bg-blue-900 text-blue-300' : 'bg-blue-100 text-blue-700',
      HIGH: darkMode ? 'bg-orange-900 text-orange-300' : 'bg-orange-100 text-orange-700',
      URGENT: darkMode ? 'bg-red-900 text-red-300' : 'bg-red-100 text-red-700'
    };
    return colors[priority] || colors.MEDIUM;
  };

  const getColumnColor = (color) => {
    const colors = {
      blue: darkMode ? 'border-blue-700 bg-blue-900/20' : 'border-blue-200 bg-blue-50',
      yellow: darkMode ? 'border-yellow-700 bg-yellow-900/20' : 'border-yellow-200 bg-yellow-50',
      green: darkMode ? 'border-green-700 bg-green-900/20' : 'border-green-200 bg-green-50'
    };
    return colors[color];
  };

  const handleDragStart = (e, task) => {
    e.dataTransfer.setData('taskId', task.id.toString());
    e.dataTransfer.setData('currentStatus', task.status);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e, newStatus) => {
    e.preventDefault();
    const taskId = parseInt(e.dataTransfer.getData('taskId'));
    const currentStatus = e.dataTransfer.getData('currentStatus');
    
    if (currentStatus !== newStatus) {
      onStatusChange(taskId, newStatus);
    }
  };

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
      {columns.map((column) => {
        const Icon = column.icon;
        const columnTasks = getTasksByStatus(column.id);
        
        return (
          <div
            key={column.id}
            className={`rounded-lg border-2 ${getColumnColor(column.color)} p-4`}
            onDragOver={handleDragOver}
            onDrop={(e) => handleDrop(e, column.id)}
          >
            {/* Column Header */}
            <div className="flex items-center justify-between mb-4">
              <div className="flex items-center gap-2">
                <Icon size={20} />
                <h3 className="font-semibold text-lg">{column.title}</h3>
              </div>
              <span className={`${darkMode ? 'bg-gray-700' : 'bg-white'} px-2 py-1 rounded-full text-sm font-semibold`}>
                {columnTasks.length}
              </span>
            </div>

            {/* Tasks */}
            <div className="space-y-3 min-h-[400px]">
              {columnTasks.length === 0 ? (
                <div className="text-center text-gray-400 mt-8">
                  No tasks
                </div>
              ) : (
                columnTasks.map((task) => (
                  <div
                    key={task.id}
                    draggable
                    onDragStart={(e) => handleDragStart(e, task)}
                    className={`${darkMode ? 'bg-gray-800 hover:bg-gray-750' : 'bg-white hover:bg-gray-50'} rounded-lg p-4 shadow-md cursor-move transition-all hover:shadow-lg border ${darkMode ? 'border-gray-700' : 'border-gray-200'}`}
                  >
                    {/* Priority Badge */}
                    <div className="flex items-center justify-between mb-2">
                      <span className={`text-xs px-2 py-1 rounded-full font-semibold ${getPriorityColor(task.priority)}`}>
                        {task.priority}
                      </span>
                      
                      {/* Action Buttons */}
                      <div className="flex gap-2">
                        <button
                          onClick={() => onEdit(task)}
                          className={`p-1 rounded ${darkMode ? 'hover:bg-gray-700' : 'hover:bg-gray-200'} transition-colors`}
                        >
                          <Edit2 size={16} />
                        </button>
                        <button
                          onClick={() => onDelete(task.id)}
                          className="p-1 rounded hover:bg-red-100 text-red-600 transition-colors"
                        >
                          <Trash2 size={16} />
                        </button>
                      </div>
                    </div>

                    {/* Task Title */}
                    <h4 className="font-semibold mb-2">{task.title}</h4>

                    {/* Task Description */}
                    {task.description && (
                      <p className={`text-sm ${darkMode ? 'text-gray-400' : 'text-gray-600'} mb-3 line-clamp-2`}>
                        {task.description}
                      </p>
                    )}

                    {/* Task Date */}
                    <div className={`text-xs ${darkMode ? 'text-gray-500' : 'text-gray-400'}`}>
                      {new Date(task.createdAt).toLocaleDateString()}
                    </div>
                  </div>
                ))
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
}

export default KanbanBoard;