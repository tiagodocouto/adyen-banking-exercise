/*
enter your query below.
please append a semicolon ; at the end of the query
*/
select m.merchantName             as merchantname,
       t.hashedShopperReference   as hashedshopperreference,
       round(avg(t.riskScore), 2) as averageriskscore,
       count(t.transactionId)     as totalnumberoftransactions
from merchant m
         left join transaction t
                   on t.merchantId = m.merchantId
where t.hashedShopperReference in (select distinct(t.hashedShopperReference)
                                   from transaction
                                   where m.merchantId = t.merchantId)
group by merchantName, hashedshopperreference
having avg(t.riskScore) >= 100.0
order by merchantName, averageriskscore desc;
