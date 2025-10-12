
import axios from 'axios';
import { API_BASE_URL } from '../utils/Constants';

const taskService = {
  // Get all tasks
  getAllTasks: async () => {
    const response = await axios.get(API_BASE_URL);
    return response.data;
  },

  // Get task by ID
  getTaskById: async (id) => {
    const response = await axios.get(`${API_BASE_URL}/${id}`);
    return response.data;
  },

  // Create new task
  createTask: async (task) => {
    const response = await axios.post(API_BASE_URL, task);
    return response.data;
  },

  // Update task
  updateTask: async (id, task) => {
    const response = await axios.put(`${API_BASE_URL}/${id}`, task);
    return response.data;
  },

  // Delete task
  deleteTask: async (id) => {
    await axios.delete(`${API_BASE_URL}/${id}`);
  },

  // Mark task as completed
  markAsCompleted: async (id) => {
    const response = await axios.patch(`${API_BASE_URL}/${id}/complete`);
    return response.data;
  },

  // Mark task as in progress
  markAsInProgress: async (id) => {
    const response = await axios.patch(`${API_BASE_URL}/${id}/in-progress`);
    return response.data;
  },

  // Mark task as todo
  markAsTodo: async (id) => {
    const response = await axios.patch(`${API_BASE_URL}/${id}/todo`);
    return response.data;
  },

  // Get tasks by status
  getTasksByStatus: async (status) => {
    const response = await axios.get(`${API_BASE_URL}/status/${status}`);
    return response.data;
  },

  // Get tasks by priority
  getTasksByPriority: async (priority) => {
    const response = await axios.get(`${API_BASE_URL}/priority/${priority}`);
    return response.data;
  },

  // Search tasks by title
  searchTasks: async (title) => {
    const response = await axios.get(`${API_BASE_URL}/search`, {
      params: { title }
    });
    return response.data;
  },

  // Get tasks ordered by priority
  getTasksOrderedByPriority: async () => {
    const response = await axios.get(`${API_BASE_URL}/order/priority`);
    return response.data;
  },

  // Get tasks ordered by date
  getTasksOrderedByDate: async () => {
    const response = await axios.get(`${API_BASE_URL}/order/date`);
    return response.data;
  }
};

export default taskService;