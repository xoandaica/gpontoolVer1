/*
 * Copyright 2015 Pivotal Software, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.vnpttech.ssdc.nms.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import vn.vnpttech.ssdc.nms.dao.PolicyHistoryDao;
import vn.vnpttech.ssdc.nms.model.PolicyHistory;
import vn.vnpttech.ssdc.nms.service.PolicyHistoryManager;

/**
 *
 * @author Dell
 */
public class PolicyHistoryManagerImpl extends GenericManagerImpl<PolicyHistory, Long> implements PolicyHistoryManager {

    @Autowired
    PolicyHistoryDao policyHistoryDao;

    public PolicyHistoryDao getPolicyHistoryDao() {
        return policyHistoryDao;
    }

    public void setPolicyHistoryDao(PolicyHistoryDao policyHistoryDao) {
        this.policyHistoryDao = policyHistoryDao;
    }

    public PolicyHistoryManagerImpl(PolicyHistoryDao policyHistoryDao) {
        super(policyHistoryDao);
    }

    public PolicyHistoryManagerImpl() {
    }

    @Override
    public List<PolicyHistory> getPolicyHistory(String serialNo, Integer status) {
        return policyHistoryDao.getPolicyHistory(serialNo, status);
    }
    
    @Override
    public Map searchPolicyHistory(String policyId, String deviceId, Long start, Long limit) {
        return policyHistoryDao.searchPolicyHistory(policyId, deviceId, start, limit);
    }

    @Override
    public void deletePolicyHistory(String deviceIdList) {
        policyHistoryDao.deletePolicyHistory(deviceIdList);
    }
}
