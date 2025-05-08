import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import GroupForm from '../GroupForm/GroupForm';

const GroupList = () => {
  const [groups, setGroups] = useState([]);
  const [selectedGroup, setSelectedGroup] = useState(null);

  const fetchGroups = async () => {
    const res = await api.get('/groups');
    setGroups(res.data);
  };

  const deleteGroup = async (id) => {
    const res = await api.delete(`/groups/${id}`);
    alert(res.data);
    fetchGroups();
  };

  useEffect(() => {
    fetchGroups();
  }, []);

  return (
    <div className="group-container">
      <h2>Skill-Based Study Groups</h2>
      <GroupForm currentGroup={selectedGroup} onSuccess={fetchGroups} />
      <ul>
        {groups.map((group) => (
          <li key={group.id}>
            <h3>{group.title}</h3>
            <p>{group.description}</p>
            <p>Created by: {group.createdBy}</p>
            <button onClick={() => setSelectedGroup(group)}>Edit</button>
            <button onClick={() => deleteGroup(group.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GroupList;
