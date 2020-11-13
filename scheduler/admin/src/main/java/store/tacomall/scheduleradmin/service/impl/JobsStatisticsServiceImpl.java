package store.tacomall.scheduleradmin.service.impl;

import store.tacomall.scheduleradmin.service.IJobsInfoService;
import store.tacomall.scheduleradmin.service.IJobsLogService;
import store.tacomall.scheduleradmin.service.IJobsRegistryService;
import store.tacomall.scheduleradmin.service.IJobsStatisticsService;
import store.tacomall.scheduleradmin.service.vo.JobsDateDistributionVO;
import store.tacomall.scheduleradmin.service.vo.JobsImportantNumVO;
import store.tacomall.scheduleradmin.service.vo.JobsSuccessRatioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsStatisticsServiceImpl implements IJobsStatisticsService {
    @Autowired
    private IJobsInfoService jobsInfoService;
    @Autowired
    private IJobsLogService jobsLogService;
    @Autowired
    private IJobsRegistryService jobsRegistryService;

    @Override
    public JobsImportantNumVO getImportantNum() {
        JobsImportantNumVO vo = new JobsImportantNumVO();
        vo.setRunTaskNum(jobsRegistryService.countAll());
        vo.setTriggeredNum(jobsLogService.countAll());
        vo.setOnlineExecutorNum(jobsRegistryService.countOnline());
        return vo;
    }

    @Override
    public JobsSuccessRatioVO getSuccessRatio() {
        JobsSuccessRatioVO vo = new JobsSuccessRatioVO();
        vo.setSuccessful(jobsLogService.countSuccess());
        vo.setFailed(jobsLogService.countAll() - vo.getSuccessful());
        return vo;
    }

    @Override
    public List<JobsDateDistributionVO> getDateDistribution() {
        return jobsLogService.getJobsDateDistributionVO();
    }
}
