import { PieChart, Pie, BarChart, Bar, LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, Cell } from 'recharts';
import { CheckCircle2, Clock, ListTodo, AlertCircle } from 'lucide-react';

function Dashboard({ tasks, darkMode }) {
  // Calculate statistics
  const totalTasks = tasks.length;
  const todoTasks = tasks.filter(t => t.status === 'TODO').length;
  const inProgressTasks = tasks.filter(t => t.status === 'IN_PROGRESS').length;
  const doneTasks = tasks.filter(t => t.status === 'DONE').length;

  // Status data for pie chart
  const statusData = [
    { name: 'To Do', value: todoTasks, color: '#3B82F6' },
    { name: 'In Progress', value: inProgressTasks, color: '#F59E0B' },
    { name: 'Done', value: doneTasks, color: '#10B981' }
  ].filter(item => item.value > 0);

  // Priority data for bar chart
  const priorityData = [
    { name: 'Low', count: tasks.filter(t => t.priority === 'LOW').length },
    { name: 'Medium', count: tasks.filter(t => t.priority === 'MEDIUM').length },
    { name: 'High', count: tasks.filter(t => t.priority === 'HIGH').length },
    { name: 'Urgent', count: tasks.filter(t => t.priority === 'URGENT').length }
  ];

  // Stats cards data
  const stats = [
    { label: 'Total Tasks', value: totalTasks, icon: ListTodo, color: 'blue' },
    { label: 'To Do', value: todoTasks, icon: Clock, color: 'blue' },
    { label: 'In Progress', value: inProgressTasks, icon: AlertCircle, color: 'yellow' },
    { label: 'Completed', value: doneTasks, icon: CheckCircle2, color: 'green' }
  ];

  const getColorClass = (color) => {
    const colors = {
      blue: darkMode ? 'bg-blue-900 border-blue-700' : 'bg-blue-50 border-blue-200',
      yellow: darkMode ? 'bg-yellow-900 border-yellow-700' : 'bg-yellow-50 border-yellow-200',
      green: darkMode ? 'bg-green-900 border-green-700' : 'bg-green-50 border-green-200'
    };
    return colors[color];
  };

  const getTextColorClass = (color) => {
    const colors = {
      blue: 'text-blue-600',
      yellow: 'text-yellow-600',
      green: 'text-green-600'
    };
    return colors[color];
  };

  return (
    <div className="space-y-6">
      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        {stats.map((stat) => {
          const Icon = stat.icon;
          return (
            <div
              key={stat.label}
              className={`${getColorClass(stat.color)} border rounded-lg p-6 transition-transform hover:scale-105`}
            >
              <div className="flex items-center justify-between">
                <div>
                  <p className={`text-sm ${darkMode ? 'text-gray-300' : 'text-gray-600'}`}>
                    {stat.label}
                  </p>
                  <p className="text-3xl font-bold mt-2">{stat.value}</p>
                </div>
                <Icon className={getTextColorClass(stat.color)} size={40} />
              </div>
            </div>
          );
        })}
      </div>

      {/* Charts */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Status Distribution - Pie Chart */}
        <div className={`${darkMode ? 'bg-gray-800' : 'bg-white'} rounded-lg p-6 shadow-lg`}>
          <h3 className="text-xl font-semibold mb-4">Tasks by Status</h3>
          {statusData.length > 0 ? (
            <ResponsiveContainer width="100%" height={300}>
              <PieChart>
                <Pie
                  data={statusData}
                  cx="50%"
                  cy="50%"
                  labelLine={false}
                  label={({ name, percent }) => `${name}: ${(percent * 100).toFixed(0)}%`}
                  outerRadius={100}
                  fill="#8884d8"
                  dataKey="value"
                >
                  {statusData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                  ))}
                </Pie>
                <Tooltip 
                  contentStyle={{
                    backgroundColor: darkMode ? '#1f2937' : '#ffffff',
                    border: `1px solid ${darkMode ? '#374151' : '#e5e7eb'}`,
                    borderRadius: '0.5rem'
                  }}
                />
              </PieChart>
            </ResponsiveContainer>
          ) : (
            <div className="h-64 flex items-center justify-center text-gray-400">
              No tasks to display
            </div>
          )}
        </div>

        {/* Priority Distribution - Bar Chart */}
        <div className={`${darkMode ? 'bg-gray-800' : 'bg-white'} rounded-lg p-6 shadow-lg`}>
          <h3 className="text-xl font-semibold mb-4">Tasks by Priority</h3>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={priorityData}>
              <CartesianGrid strokeDasharray="3 3" stroke={darkMode ? '#374151' : '#e5e7eb'} />
              <XAxis 
                dataKey="name" 
                stroke={darkMode ? '#9ca3af' : '#6b7280'}
              />
              <YAxis stroke={darkMode ? '#9ca3af' : '#6b7280'} />
              <Tooltip 
                contentStyle={{
                  backgroundColor: darkMode ? '#1f2937' : '#ffffff',
                  border: `1px solid ${darkMode ? '#374151' : '#e5e7eb'}`,
                  borderRadius: '0.5rem'
                }}
              />
              <Bar dataKey="count" fill="#3B82F6" radius={[8, 8, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>

      {/* Completion Rate */}
      <div className={`${darkMode ? 'bg-gray-800' : 'bg-white'} rounded-lg p-6 shadow-lg`}>
        <h3 className="text-xl font-semibold mb-4">Completion Rate</h3>
        <div className="space-y-2">
          <div className="flex justify-between text-sm">
            <span>Progress</span>
            <span className="font-semibold">
              {totalTasks > 0 ? Math.round((doneTasks / totalTasks) * 100) : 0}%
            </span>
          </div>
          <div className={`w-full ${darkMode ? 'bg-gray-700' : 'bg-gray-200'} rounded-full h-4 overflow-hidden`}>
            <div
              className="bg-green-500 h-full rounded-full transition-all duration-500"
              style={{ width: `${totalTasks > 0 ? (doneTasks / totalTasks) * 100 : 0}%` }}
            />
          </div>
          <p className="text-sm text-gray-500 mt-2">
            {doneTasks} of {totalTasks} tasks completed
          </p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;