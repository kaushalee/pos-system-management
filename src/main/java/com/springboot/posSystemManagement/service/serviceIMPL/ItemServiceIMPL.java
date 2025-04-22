package com.springboot.posSystemManagement.service.serviceIMPL;

import com.springboot.posSystemManagement.dto.pagination.ItemPaginationDTO;
import com.springboot.posSystemManagement.dto.request.ItemRequestDTO;
import com.springboot.posSystemManagement.dto.response.ItemResponseDTO;
import com.springboot.posSystemManagement.dto.update.ItemUpdateDTO;
import com.springboot.posSystemManagement.entity.ItemEntity;
import com.springboot.posSystemManagement.exception.NotFoundException;
import com.springboot.posSystemManagement.repo.ItemRepo;
import com.springboot.posSystemManagement.service.ItemService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveItem(ItemRequestDTO itemRequestDTO) {

        ItemEntity itemEntity = new ItemEntity(  //constructor create kala  item entity eke
                itemRequestDTO.getItemName(),
                itemRequestDTO.getItemQty(),
                itemRequestDTO.getItemSupplyPrice(),
                itemRequestDTO.getItemSellPrice(),
                itemRequestDTO.getItemDiscount(),
                itemRequestDTO.getDiscountWithPercentage()
        );

        /*  for loop waladi request yawadi   (set<oder details> / item id / item active State) data request eken awanne nathi nisa
        public ItemEntity(String itemName, double itemQty, double itemSupplyPrice, double itemSellPrice, double itemDiscount, double discountWithPercentage) {
            this.itemName = itemName;
            this.itemQty = itemQty;
            this.itemSupplyPrice = itemSupplyPrice;
            this.itemSellPrice = itemSellPrice;
            this.itemDiscount = itemDiscount;
            this.discountWithPercentage = discountWithPercentage;
        }*/
        itemRepo.save(itemEntity);
        return itemRequestDTO.getItemName().concat("is saved.");
    }

    @Override
    public String updateItem(ItemUpdateDTO itemUpdateDTO) {
        if (itemRepo.existsById(itemUpdateDTO.getItemId())) {
            ItemEntity itemEntity = itemRepo.getReferenceById(itemUpdateDTO.getItemId());
            itemEntity.setItemQty(itemUpdateDTO.getItemQty());
            itemEntity.setItemSupplyPrice(itemUpdateDTO.getItemSupplyPrice());
            itemEntity.setItemSellPrice(itemUpdateDTO.getItemSellPrice());
            itemEntity.setItemDiscount(itemUpdateDTO.getItemDiscount());
            itemEntity.setDiscountWithPercentage(itemUpdateDTO.getDiscountWithPercentage());

            itemRepo.save(itemEntity);
            return itemUpdateDTO.getItemId() + " is updated";
        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public String deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)) {

            ItemEntity itemEntity = itemRepo.getReferenceById(itemId);
            itemRepo.delete(itemEntity);
            return itemId + " is deleted.";
        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public ItemResponseDTO getItem(int itemId) {
        if (itemRepo.existsById(itemId)) {

            ItemEntity itemEntity = itemRepo.getReferenceById(itemId);
            ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                    itemEntity.getItemId(),
                    itemEntity.getItemName(),
                    itemEntity.getItemQty(),
                    itemEntity.getItemSupplyPrice(),
                    itemEntity.getItemSellPrice(),
                    itemEntity.getItemDiscount(),
                    itemEntity.getDiscountWithPercentage(),
                    itemEntity.isItemActiveState()

            );
            return itemResponseDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public ItemPaginationDTO getItemByNameContains(String itemName, int page, int size) {
        boolean activeState = true;
        Page<ItemEntity> itemEntityPage = itemRepo.findAllByItemNameContainsAndItemActiveStateEquals(itemName, PageRequest.of(page, size), activeState);
        long count = itemRepo.countAllByItemNameContainsAndItemActiveStateEquals(itemName, activeState);
        if (count > 0) {    //employeeEntityPage.size()>0

            List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();   //custom class to arry list - wenuwat wrapper data type and non primitive data type
            for (ItemEntity itemEntity : itemEntityPage) {

                ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                        itemEntity.getItemId(),
                        itemEntity.getItemName(),
                        itemEntity.getItemQty(),
                        itemEntity.getItemSupplyPrice(),
                        itemEntity.getItemSellPrice(),
                        itemEntity.getItemDiscount(),
                        itemEntity.getDiscountWithPercentage(),
                        itemEntity.isItemActiveState()
                );
                itemResponseDTOList.add(itemResponseDTO);
            }


            ItemPaginationDTO itemPaginationDTO = new ItemPaginationDTO(
                    itemResponseDTOList, count
            );
            return itemPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public ItemPaginationDTO getItemLessThanDiscountPercentage(double itemDiscountPercentage, int page) {
        boolean activeState = true;
        int size = 3;
        Page<ItemEntity> itemEntityPage = itemRepo.findAllByDiscountWithPercentageLessThanAndItemActiveStateEquals(itemDiscountPercentage, PageRequest.of(page, size), activeState);
        long count = itemRepo.countAllByDiscountWithPercentageLessThanAndItemActiveStateEquals(itemDiscountPercentage, activeState);
        if (count > 0) {//itemEntityPage.SIze()>0
            List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
            for (ItemEntity itemEntity : itemEntityPage) {

                ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                        itemEntity.getItemId(),
                        itemEntity.getItemName(),
                        itemEntity.getItemQty(),
                        itemEntity.getItemSupplyPrice(),
                        itemEntity.getItemSellPrice(),
                        itemEntity.getItemDiscount(),
                        itemEntity.getDiscountWithPercentage(),
                        itemEntity.isItemActiveState()
                );
                itemResponseDTOList.add(itemResponseDTO);
            }
            ItemPaginationDTO itemPaginationDTO = new ItemPaginationDTO(
                    itemResponseDTOList, count
            );
            return itemPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


    @Override
    public ItemPaginationDTO getAllItemStateIsTrue(int page) {
        boolean activeState = true;
        int size = 3;
        Page<ItemEntity> itemEntityPage = itemRepo.findAllByItemActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = itemRepo.countAllByItemActiveStateEquals(activeState);
        if (count > 0) {//itemEntityPage.SIze()>0

            List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
            for (ItemEntity itemEntity : itemEntityPage) {

                ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                        itemEntity.getItemId(),
                        itemEntity.getItemName(),
                        itemEntity.getItemQty(),
                        itemEntity.getItemSupplyPrice(),
                        itemEntity.getItemSellPrice(),
                        itemEntity.getItemDiscount(),
                        itemEntity.getDiscountWithPercentage(),
                        itemEntity.isItemActiveState()
                );
                itemResponseDTOList.add(itemResponseDTO);
            }
            ItemPaginationDTO itemPaginationDTO = new ItemPaginationDTO(
                    itemResponseDTOList, count
            );
            return itemPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public ItemPaginationDTO getAllItem(int page, int size) {
        Page<ItemEntity> itemEntityPage = itemRepo.findAll(PageRequest.of(page, size));
        long count = itemRepo.count();
        if (count > 0) {//customerEntityList.SIze()>0

            List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();

            for (ItemEntity itemEntity : itemEntityPage) {

                ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                        itemEntity.getItemId(),
                        itemEntity.getItemName(),
                        itemEntity.getItemQty(),
                        itemEntity.getItemSupplyPrice(),
                        itemEntity.getItemSellPrice(),
                        itemEntity.getItemDiscount(),
                        itemEntity.getDiscountWithPercentage(),
                        itemEntity.isItemActiveState()
                );
                itemResponseDTOList.add(itemResponseDTO);
            }
            ItemPaginationDTO itemPaginationDTO = new ItemPaginationDTO(
                    itemResponseDTOList, count
            );
            return itemPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }

    @Override
    public ItemPaginationDTO getAllItemWithState(boolean activeState, int page) {

        int size = 3;
        Page<ItemEntity> itemEntityPage = itemRepo.findAllByItemActiveStateEquals(activeState, PageRequest.of(page, size));
        long count = itemRepo.countAllByItemActiveStateEquals(activeState);
        if (count > 0) {//customerEntityList.SIze()>0

            List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();

            for (ItemEntity itemEntity : itemEntityPage) {

                ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                        itemEntity.getItemId(),
                        itemEntity.getItemName(),
                        itemEntity.getItemQty(),
                        itemEntity.getItemSupplyPrice(),
                        itemEntity.getItemSellPrice(),
                        itemEntity.getItemDiscount(),
                        itemEntity.getDiscountWithPercentage(),
                        itemEntity.isItemActiveState()
                );
                itemResponseDTOList.add(itemResponseDTO);
            }
            ItemPaginationDTO itemPaginationDTO = new ItemPaginationDTO(
                    itemResponseDTOList, count
            );
            return itemPaginationDTO;

        } else {
            throw new NotFoundException("not found");
        }
    }


}



